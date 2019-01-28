package place

import agent.Bus
import agent.Vehicle
import java.awt.Color
import java.awt.Graphics2D
import kotlin.math.pow
import kotlin.math.sqrt

class Roadway(override val previous: Node, override val next: Node, override val meters: Int, val busFreq: Int, val busCost: Int) :
    Way {


    override val cellMaxAgents = 1
    val cellNum = meters/100
    var vehicles: Array<ArrayList<Vehicle>>
    override var isHorizontal = false

    init {
        addSelfToNodes()
        vehicles = Array(cellNum) { ArrayList<Vehicle>() }
    }

    override fun checkAllAgents(time: Int) {
        for (i in 0 until cellNum){
            val index = cellNum-i-1 // cellのindex.後ろから探索.
            if(index == cellNum-1) { // 最後のマス
                val removingVehicles = ArrayList<Vehicle>()
                for(vehicle in vehicles[index]){
                    if (next is Goal){ // 人がnextに移動し、車は消滅
                        vehicle.people.forEach {
                            next.people.add(it)
                            it.arrivedTime = time
                        }
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
        var x1 = previous.point.x.toDouble()    // 添字1がprevious側
        val x2 = next.point.x.toDouble()
        var y1 = previous.point.y.toDouble()
        var y2 = next.point.y.toDouble()

        if (previous is Start && next.name == "塚口（JR）") {
            x1 -= width*3
        }
        y1 += if (y1<y2) Node.radius else -Node.radius
        y2 -= if (y1<y2) Node.radius else -Node.radius
        val wayLength = sqrt((x2-x1).pow(2) + (y2-y1).pow(2)).toInt()
        val wayXLength = sqrt((x2-x1).pow(2) ).toInt()

//        val X1p = x1 + width
//        val Y1p = y1
//        val X1m = x1+1
//        val Y1m = y1
//        val X2p = x2 + width
//        val Y2p = y2
//        val X2m = x2+1
//        val Y2m = y2
//
//        g.color = Color.YELLOW
//        g.drawLine(X1p.toInt(), Y1p.toInt(), X1m.toInt(), Y1m.toInt())
//        g.drawLine(X2p.toInt(), Y2p.toInt(), X2m.toInt(), Y2m.toInt())
//        g.drawLine(X1p.toInt(), Y1p.toInt(), X2p.toInt(), Y2p.toInt())
//        g.drawLine(X1m.toInt(), Y1m.toInt(), X2m.toInt(), Y2m.toInt())

        g.color = Color.BLACK
        var count = 0
        for (cell in vehicles){
            count += cell.size
        }
        g.drawString("走行車の総数: $count", ((x1+x2)/2 + 20).toInt(), ((y1+y2)/2).toInt())

        // cellの表示
        val cellDotSize = wayLength/vehicles.size
        val cellXLength = wayXLength/vehicles.size
        for ((index, cell) in vehicles.withIndex()){
//            val celly = y1 + index * cellDotSize // TODO: いい感じになるように座標計算
            val cellx = if(x1<x2) x1 + index * cellXLength else x1 - (index+1) * cellXLength
            val celly = if(y1<y2) y1 + index * cellDotSize else y1 - (index+1) * cellDotSize

            val ratio = cell.size/cellMaxAgents.toDouble()
            when{
                ratio < 0.3 -> g.color = Color.BLUE
                ratio > 0.7 -> g.color = Color.RED
                else        -> g.color = Color.ORANGE
            }
            if (cell.any { it is Bus }){ g.color = Color.PINK }
            if (isHorizontal) {
                g.fillRect(cellx.toInt(), celly.toInt(), cellXLength, width.toInt())
            }else {
                g.fillRect(cellx.toInt(), celly.toInt(), width.toInt(), cellDotSize)
            }

            g.color = Color.YELLOW
            g.drawRect(cellx.toInt(), celly.toInt(), width.toInt(), cellDotSize)
        }
    }
}