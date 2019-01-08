package place

import agent.Car
import agent.Person
import java.awt.Color
import java.awt.Graphics2D

class Sidewalk(override val previous: Node, override val next: Node, override val length: Int) : Way {

    override lateinit var cellsAgentNum: Array<Int>
    var agents = ArrayList<Person>()
    override val cellMaxAgents = 1
    var people: Array<ArrayList<Person>>

    init {
        addSelfToNodes()
        val cellNum = length/100
        cellsAgentNum = Array(cellNum) { 0 }
        people = Array(cellNum) { ArrayList<Person>() }
    }
    override fun drawSelf(g: Graphics2D) {

        val width = 10.0
        val x1 = previous.point.x.toDouble()    // 添字1がprevious側
        val x2 = next.point.x.toDouble()
        val y1 = previous.point.y.toDouble()
        val y2 = next.point.y.toDouble()

        val X1p = x1
        val Y1p = y1
        val X1m = x1 - width
        val Y1m = y1
        val X2p = x2
        val Y2p = y2
        val X2m = x2 - width
        val Y2m = y2

        g.color = Color.MAGENTA
        g.drawLine(X1p.toInt(), Y1p.toInt(), X1m.toInt(), Y1m.toInt())
        g.drawLine(X2p.toInt(), Y2p.toInt(), X2m.toInt(), Y2m.toInt())
        g.drawLine(X1p.toInt(), Y1p.toInt(), X2p.toInt(), Y2p.toInt())
        g.drawLine(X1m.toInt(), Y1m.toInt(), X2m.toInt(), Y2m.toInt())
    }
}