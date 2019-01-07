package place

import java.awt.Color
import java.awt.Graphics2D
import java.awt.Point

interface Node: Place{
    var name: String
    var ways: ArrayList<Way>
    var point: Point

    override fun drawSelf(g: Graphics2D) {
        g.color = Color.BLACK
        g.drawString(name, point.x, point.y-30)
    }
}