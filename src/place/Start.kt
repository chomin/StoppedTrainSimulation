package place

import java.awt.Color
import java.awt.Graphics2D
import java.awt.Point

class Start(override var point: Point) : Node {

    override var name = "塚口（阪急）"
    override var ways = ArrayList<Way>()

    override fun drawSelf(g: Graphics2D) {
        super.drawSelf(g)
        val radius = 20
        g.color = Color.BLUE
        g.fillOval(point.x-radius, point.y-radius, radius*2, radius*2)
    }

}