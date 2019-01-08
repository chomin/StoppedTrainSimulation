package place

import agent.Person
import agent.Train
import java.awt.Color
import java.awt.Graphics2D
import java.awt.Point

class Station(override var name: String, override var point: Point): Node {

    override var ways = ArrayList<Way>()
    val prevs = ArrayList<Way>()
    val nexts = ArrayList<Way>()
    val maxPeopleNum = 300
    val people = ArrayList<Person>()
    val maxTrainNum = 1
    val trains = ArrayList<Train>()

    init {
        for (way in ways){
            if (way.previous == this){
                nexts.add(way)
            }else{
                prevs.add(way)
            }
        }
    }

    override fun drawSelf(g: Graphics2D) {
        super.drawSelf(g)
        val radius = 20
        g.color = Color.GREEN
        g.fillOval(point.x-radius, point.y-radius, radius*2, radius*2)
    }
}