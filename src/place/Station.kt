package place

import agent.*
import java.awt.Color
import java.awt.Graphics2D
import java.awt.Point
import java.util.*

class Station(override var name: String, override var point: Point): Node {

    override var ways = ArrayList<Way>()
    override val people = ArrayList<Person>()
    val prevs = ArrayList<Way>()
    val nexts = ArrayList<Way>()
    val maxPeopleNum = 300
    val maxTrainNum = 1
    private val waitingTrains   = ArrayList<Pair<RailWay, Train   >>()
    private val waitingVehicles = ArrayList<Pair<Roadway, Vehicle >>()

    init {
        for (way in ways){
            if (way.previous == this){
                nexts.add(way)
            }else{
                prevs.add(way)
            }
        }
    }

    override fun generateCars(num: Int) {
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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun checkAllAgents() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun drawSelf(g: Graphics2D) {
        super.drawSelf(g)
        val radius = 20
        g.color = Color.GREEN
        g.fillOval(point.x-radius, point.y-radius, radius*2, radius*2)
    }
}