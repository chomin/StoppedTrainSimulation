package place

import agent.Person
import agent.Vehicle
import java.awt.Color
import java.awt.Graphics2D
import java.awt.Point

class Goal(override var point: Point): Node {

    override var name = "六甲駅"
    override var ways = ArrayList<Way>()
    override val people = ArrayList<Person>()
    override val waitingVehicles = ArrayList<Pair<Roadway, Vehicle>>()


    override fun checkAllAgents() {}
    override fun generateCars(num: Int, time: Int) {}
    override fun generateTrains(num: Int, time: Int) {}

    override fun drawSelf(g: Graphics2D) {
        super.drawSelf(g)
        g.color = Color.RED
        g.fillOval(point.x-Node.radius, point.y-Node.radius, Node.radius*2, Node.radius*2)
    }


}