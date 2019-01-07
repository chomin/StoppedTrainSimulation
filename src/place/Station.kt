package place

import java.awt.Color
import java.awt.Graphics2D
import java.awt.Point

class Station(override var name: String, override var point: Point): Node {
    override var ways = ArrayList<Way>()

    override fun drawSelf(g: Graphics2D) {
        super.drawSelf(g)
        val radius = 20
        g.color = Color.GREEN
        g.fillOval(point.x-radius, point.y-radius, radius*2, radius*2)
    }
}