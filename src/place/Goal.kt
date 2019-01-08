package place

import agent.Person
import java.awt.Color
import java.awt.Graphics2D
import java.awt.Point

class Goal(override var point: Point): Node {

    override var name = "六甲駅"
    override var ways = ArrayList<Way>()
    override val people = ArrayList<Person>()

    override fun checkAllAgents() {}
    override fun generateVehicles() {}

    override fun drawSelf(g: Graphics2D) {
        super.drawSelf(g)
        val radius = 20
        g.color = Color.RED
        g.fillOval(point.x-radius, point.y-radius, radius*2, radius*2)
    }



}