package place

import agent.Person
import java.awt.Color
import java.awt.Graphics2D
import java.awt.Point

interface Node: Place{
    var name: String
    var ways: ArrayList<Way>
    var point: Point
    val people: ArrayList<Person>

    fun generateCars(num: Int)
    fun generateTrains(num: Int)

    override fun drawSelf(g: Graphics2D) {
        g.color = Color.BLACK
        g.drawString(name, point.x, point.y-30)
    }
}