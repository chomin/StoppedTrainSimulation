package place

import agent.Train
import agent.Vehicle
import java.awt.Color
import java.awt.Graphics2D

class Roadway(override val previous: Node, override val next: Node, override val length: Int, val busFreq: Int) :
    Way {


    override val cellMaxAgents = 1
    val cellNum = length/100
    var vehicles: Array<ArrayList<Vehicle>>

    init {
        addSelfToNodes()
        vehicles = Array(cellNum) { ArrayList<Vehicle>() }
    }

    override fun checkAllAgents() {
        for (i in 0 until cellNum){
            val index = cellNum-i-1 // cellのindex.後ろから探索.
            if(index == cellNum-1) { // 最後のマス
                val removingVehicles = ArrayList<Vehicle>()
                for(vehicle in vehicles[index]){
                    if (next is Goal){ // 人がnextに移動し、車は消滅
                        vehicle.people.forEach { next.people.add(it) }
                        removingVehicles.add(vehicle)
                    } else { // nextはStation
                        val nextSta = next as Station
                        if (nextSta.people.size + vehicle.people.size <= nextSta.maxPeopleNum){
                            vehicle.people.forEach { next.people.add(it) }
                            removingVehicles.add(vehicle)
                        }
                    }
                }
                for(vehicle in removingVehicles){ vehicles[index].remove(vehicle) } // 車を消滅させる
            } else {
                val removingVehicles = ArrayList<Vehicle>()
                for(vehicle in vehicles[index]){
                    if (vehicles[index+1].size < cellMaxAgents){ // 車を進める
                        vehicles[index+1].add(vehicle)
                        removingVehicles.add(vehicle)
                    }
                }
                for(vehicle in removingVehicles){ vehicles[index].remove(vehicle) } // 移動したものを除去
            }
        }
    }

    override fun drawSelf(g: Graphics2D) {
        val width = 10.0
        val x1 = previous.point.x.toDouble()    // 添字1がprevious側
        val x2 = next.point.x.toDouble()
        val y1 = previous.point.y.toDouble()
        val y2 = next.point.y.toDouble()

        val X1p = x1 + width
        val Y1p = y1
        val X1m = x1+1
        val Y1m = y1
        val X2p = x2 + width
        val Y2p = y2
        val X2m = x2+1
        val Y2m = y2

        g.color = Color.YELLOW
        g.drawLine(X1p.toInt(), Y1p.toInt(), X1m.toInt(), Y1m.toInt())
        g.drawLine(X2p.toInt(), Y2p.toInt(), X2m.toInt(), Y2m.toInt())
        g.drawLine(X1p.toInt(), Y1p.toInt(), X2p.toInt(), Y2p.toInt())
        g.drawLine(X1m.toInt(), Y1m.toInt(), X2m.toInt(), Y2m.toInt())
    }
}