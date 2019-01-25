package place

import agent.Person
import agent.Vehicle
import java.awt.Color
import java.awt.Graphics2D
import java.awt.Point

interface Node: Place{

    companion object {
        val radius = 20
    }

    var name: String
    var ways: ArrayList<Way>
    var point: Point
    val people: ArrayList<Person>
    val waitingVehicles: ArrayList<Pair<Roadway, Vehicle>>

    fun generateCars(time: Int)
    fun generateTrains(time: Int)

    override fun drawSelf(g: Graphics2D) {
        g.color = Color.BLACK
        g.drawString(name, point.x+10, point.y-60)
        g.drawString("人数: " + people.size, point.x+10, point.y-30)
        g.drawString("車の数: " + waitingVehicles.size, point.x+10, point.y+30)
    }
}