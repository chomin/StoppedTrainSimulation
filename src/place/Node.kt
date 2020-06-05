package place

import agent.Person
import agent.Vehicle
import java.awt.Color
import java.awt.Font
import java.awt.Graphics2D
import java.awt.Point

interface Node : Place {

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
        val font0 = g.font
        val font = Font("DailogInput", Font.BOLD, 16)
        g.font = font
        if (name == "塚口（JR）") {
            g.drawString(name, point.x - 100, point.y - 60)
        } else {
            g.drawString(name, point.x + 10, point.y - 60)
        }

        g.font = font0
        g.drawString("人数: " + people.size, point.x + 10, point.y - 30)
        g.drawString("車の数: " + waitingVehicles.size, point.x + 10, point.y + 30)
    }
}