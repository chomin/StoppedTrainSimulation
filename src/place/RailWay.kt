package place

import java.awt.Color
import java.awt.Graphics2D

class RailWay(override val previous: Node, override val next: Node, override val length: Int, val trainFreq: Int) :
    Way {
    override lateinit var cells: Array<Double>
    init {
        addSelfToNodes()
        val cellNum: Int = length/1000
        cells = Array(cellNum) { 0.0 }

    }

    override fun drawSelf(g: Graphics2D) {
        val radius = 10
        g.color = Color.BLACK
        g.drawLine(previous.point.x, previous.point.y, next.point.x, next.point.y)
    }
}