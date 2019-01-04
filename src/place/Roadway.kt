package place

import agent.Vehicle
import java.awt.Color
import java.awt.Graphics2D

class Roadway(override val previous: Node, override val next: Node, override val length: Int, val busFreq: Int) :
    Way {
    override lateinit var cells: Array<Double>
    var agents = ArrayList<Vehicle>()
    init {
        addSelfToNodes()
        cells = Array(length/100) { 0.0 }
    }
    override fun drawSelf(g: Graphics2D) {
        val radius = 10
        g.color = Color.BLACK
        g.drawLine(previous.point.x, previous.point.y, next.point.x, next.point.y)
    }
}