package place

import agent.Bus
import agent.Person
import agent.Strategy
import agent.Vehicle
import java.awt.Color
import java.awt.Graphics2D
import java.awt.Point
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.pow

class Start(override var point: Point, override val people: ArrayList<Person>) : Node {// 最初はみんなpeopleに入るが、バスに乗ると取り除かれる。


    override var name = "塚口（阪急）"
    override var ways = ArrayList<Way>()

    override val waitingVehicles = ArrayList<Pair<Roadway, Vehicle>>()

    private fun generateACar(road: Roadway) {
        // 乗る人を決め、バスを出発or待機させる
        val ridingPeople = ArrayList<Person>()
        val ran = Random()
        for (person in people) {
            if (ridingPeople.size > Bus.maxPeople) break
            when (person.strategy){
                Strategy.Normal -> {
                    val tmp = ran.nextInt(2)
                    when (tmp) {
                        0 -> {
                            ridingPeople.add(person)
                            person.totalCost += road.busCost
                        }
                    }
                }
                Strategy.BusOnly -> {
//                    if (road.next.name == "塚口（JR）") {
                        ridingPeople.add(person)
                        person.totalCost += road.busCost
//                    }
                }
                Strategy.NoCash  -> {}   // 乗らない
                Strategy.ViaNishikita -> {}

//                Strategy.AmagasakiBus -> {
//                    if (road.next.name == "尼崎") {
//                        ridingPeople.add(person)
//                        person.totalCost += road.busCost
//                    }
//                }
                Strategy.AmagasakiWalk -> {}
            }
        }
        for (person in ridingPeople) { people.remove(person) }

        val bus = Bus(ridingPeople)
        if (road.vehicles[0].size < road.cellMaxAgents && waitingVehicles.none { it.first == road }) {
            road.vehicles[0].add(bus)
        } else {
            waitingVehicles.add(Pair(road, bus))
        }
    }

    override fun generateCars(time: Int) {

        val roads = ways.filter { it is Roadway }.map { it as Roadway }

        roads.forEach { if( time%it.busFreq==0 ){ generateACar(it) }}
    }

    override fun generateTrains(time: Int) { // 運行再開用
        return
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * 車に乗る人が決まったことを前提とするため、generateCars()後に呼び出すこと。
     */
    override fun checkAllAgents(time: Int) {
        val removingVP = ArrayList<Pair<Roadway, Vehicle>>()
        for (vehiclePair in waitingVehicles){
            if (vehiclePair.first.vehicles[0].size < vehiclePair.first.cellMaxAgents) {
                vehiclePair.first.vehicles[0].add(vehiclePair.second)
                removingVP.add(vehiclePair)
            }
        }
        for (vehiclePair in removingVP){ waitingVehicles.remove(vehiclePair) }

        val sidewalks = ways.filter { it is Sidewalk }.map { it as Sidewalk }
        val removingPeople = ArrayList<Person>()
        for (sidewalk in sidewalks) {   // どの道を選ぶか？は以後実装予定
            for (person in people){
//                if (person.strategy == Strategy.BusOnly || person.strategy == Strategy.AmagasakiBus) continue
                if (person.strategy == Strategy.BusOnly) continue
                if (person.strategy == Strategy.ViaNishikita && sidewalk.next.name != "西宮北口")     continue
                if (person.strategy == Strategy.NoCash && sidewalk.next.name != "塚口（JR）")         continue
                if (person.strategy == Strategy.AmagasakiWalk && sidewalk.next.name != "尼崎")       continue



                if(sidewalk.people[0].size < sidewalk.cellMaxAgents){
                    sidewalk.people[0].add(person)
                    removingPeople.add(person)
                }
            }
            for (person in removingPeople){ people.remove(person) }
        }

    }

    override fun drawSelf(g: Graphics2D) {
        super.drawSelf(g)
        g.color = Color.BLUE
        g.fillOval(point.x-Node.radius*3, point.y-Node.radius, Node.radius*4, Node.radius*2)
    }

}