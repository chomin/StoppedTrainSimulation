package place

import agent.*
import java.awt.Color
import java.awt.Graphics2D
import java.awt.Point
import java.util.*
import kotlin.collections.ArrayList

class Station(override var name: String, override var point: Point): Node {

    override var ways = ArrayList<Way>()
    override val people = ArrayList<Person>()
    val prevs = ArrayList<Way>()    // waysのノードの代入の都合上, 後でway側から初期化
    val nexts = ArrayList<Way>()
    val maxPeopleNum = 300
    val maxTrainNum = 1
    val waitingTrains   = ArrayList<Pair<RailWay, Train   >>()
    override val waitingVehicles = ArrayList<Pair<Roadway, Vehicle >>()


//    init {
//        for (way in ways){
//            if (way.previous == this){
//                nexts.add(way)
//            }else{
//                prevs.add(way)
//            }
//        }
//    }

    override fun generateCars(num: Int) {   // TODO: 将来的には人の行先によって電車に乗るか車に乗るかを歩くかを決める.
        val nextRoads = nexts.filter { it is Roadway }.map { it as Roadway }
        for (road in nextRoads) {   // すべての道に対しnumずつ生成
            for (temp in 0 until num){
                // 乗る人を決め、バスを出発or待機させる
                val ridingPeople = ArrayList<Person>()
                val ran = Random()
                for (person in people){
                    if (ridingPeople.size > Bus.maxPeople) break
                    when{
                        person.strategy == Strategy.Normal -> {
                            val tmp = ran.nextInt(2)
                            when (tmp) {
                                0 -> {
                                    ridingPeople.add(person)
                                }
                            }
                        }
                    }
                }
                for (person in ridingPeople){ people.remove(person) }
                val bus = Bus(ridingPeople)
                if (road.vehicles[0].size < road.cellMaxAgents && waitingVehicles.none { it.first == road }){
                    road.vehicles[0].add(bus)
                } else {
                    waitingVehicles.add(Pair(road, bus))
                }
            }
        }
    }

    override fun generateTrains(num: Int) {
        val nextRails = nexts.filter { it is RailWay }.map { it as RailWay }

        loop@ for (railWay in nextRails) {   // すべての道に対しnumずつ生成
            for (temp in 0 until num){
                if (waitingTrains.size >= maxTrainNum) {
                    println("駅のホームが足りません。@" + this.name)
                    break@loop
                }
                val ridingPeople = ArrayList<Person>()
                val ran = Random()
                for (person in people){
                    if (ridingPeople.size > Train.maxPeople) break
                    ridingPeople.add(person)
                }
                for (person in ridingPeople){ people.remove(person) }
                waitingTrains.add(Pair(railWay, Train(ridingPeople)))
            }
        }
    }

    override fun checkAllAgents() {
//        if(name == "塚口（JR）"){
//            println("駅の人口増減")
//            println(people.size)
//        }



        // 車と歩行者出発→電車出発→電車から人を(無理やり)下ろす
        val removingVP = ArrayList<Pair<Roadway, Vehicle>>()
        for (vehiclePair in waitingVehicles){
            if (vehiclePair.first.vehicles[0].size < vehiclePair.first.cellMaxAgents) {
                vehiclePair.first.vehicles[0].add(vehiclePair.second)
                removingVP.add(vehiclePair)
            }
        }
        for (vehiclePair in removingVP){ waitingVehicles.remove(vehiclePair) }

        val sidewalks = nexts.filter { it is Sidewalk }.map { it as Sidewalk }
        val removingPeople = ArrayList<Person>()
        for (sidewalk in sidewalks) {   // どの道を選ぶか？は以後実装予定
            for (person in people){
                if(sidewalk.people[0].size < sidewalk.cellMaxAgents){
                    sidewalk.people[0].add(person)
                    removingPeople.add(person)
                }
            }
            for (person in removingPeople){ people.remove(person) }
        }

        val removingTP = ArrayList<Pair<RailWay, Train>>()  // 駅から出発する電車を取り除く
        for (trainPair in waitingTrains){
            if (trainPair.first.trains[0].size < trainPair.first.cellMaxAgents
                && trainPair.second.state == TrainState.GotOn) {    // 出発させる
                trainPair.second.state = TrainState.Running
                trainPair.first.trains[0].add(trainPair.second)
                removingTP.add(trainPair)

            } else if (trainPair.second.state == TrainState.Running) { // 人を下ろし、電車は消滅 TODO: 次の行き先があるなら人を乗せる(なければ消滅)


                trainPair.second.state = TrainState.GotOff
                val removingPeople = ArrayList<Person>()
                for (person in trainPair.second.people){
                    people.add(person)
                    removingPeople.add(person)
                }
                for (person in removingPeople) { trainPair.second.people.remove(person) }

                removingTP.add(trainPair)
            }
        }
        for (trainPair in removingTP){ waitingTrains.remove(trainPair) }

//        if(name == "塚口（JR）"){
//            println(people.size)
//        }
    }

    override fun drawSelf(g: Graphics2D) {
        super.drawSelf(g)
        g.color = Color.BLACK
        g.drawString("電車の数: " + waitingTrains.size, point.x+10, point.y+45)
        g.color = Color.GREEN
        g.fillOval(point.x-Node.radius, point.y-Node.radius, Node.radius*2, Node.radius*2)
    }
}