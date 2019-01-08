package place

import agent.Car
import agent.Vehicle
import java.awt.Color
import java.awt.Graphics2D

class Roadway(override val previous: Node, override val next: Node, override val length: Int, val busFreq: Int) :
    Way {

    override lateinit var cellsAgentNum: Array<Int>
    var agents = ArrayList<Vehicle>()
    override val cellMaxAgents = 1
    var cars: Array<ArrayList<Car>>

    init {
        addSelfToNodes()
        val cellNum = length/100
        cellsAgentNum = Array(cellNum) { 0 }
        cars = Array(cellNum) { ArrayList<Car>() }
    }

    override fun checkAllAgents() {}
    override fun checkAgent() {}

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