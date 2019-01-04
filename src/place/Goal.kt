package place

import java.awt.Color
import java.awt.Graphics2D
import java.awt.Point

class Goal(override var point: Point): Node {

    override var name = "六甲駅"
    override var ways = ArrayList<Way>()

    override fun drawSelf(g: Graphics2D) {
        val radius = 10
        g.color = Color.RED
        g.fillOval(point.x-radius, point.y-radius, radius*2, radius*2)
    }



}