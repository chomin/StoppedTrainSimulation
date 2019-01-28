package place

import agent.Person
import agent.Strategy
import agent.Vehicle
import java.awt.Color
import java.awt.Graphics2D
import java.awt.Point
import SimulationView
import kotlin.math.ceil

class Goal(override var point: Point): Node {

    override var name = "六甲駅"
    override var ways = ArrayList<Way>()
    override val people = ArrayList<Person>()
    override val waitingVehicles = ArrayList<Pair<Roadway, Vehicle>>()

    lateinit var view: SimulationView


    override fun checkAllAgents(time: Int) {}
    override fun generateCars(time: Int) {}
    override fun generateTrains(time: Int) {}

    override fun drawSelf(g: Graphics2D) {
        super.drawSelf(g)
        g.color = Color.RED
        g.fillOval(point.x-Node.radius, point.y-Node.radius, Node.radius*2, Node.radius*2)

        val normals   = people.filter { it.strategy == Strategy.Normal  }
        val busOnlies = people.filter { it.strategy == Strategy.BusOnly }
        val noCashes  = people.filter { it.strategy == Strategy.NoCash  }
        val viaNishikitas = people.filter { it.strategy == Strategy.ViaNishikita  }
        val amagasakiWalks = people.filter { it.strategy == Strategy.AmagasakiWalk  }
        val normalNum  = normals.size
        val busOnlyNum = busOnlies.size
        val noCashNum  = noCashes.size
        val amagasakiNum = amagasakiWalks.size
        val viaNishikitaNum = viaNishikitas.size
        val normalAveTime  = ceil(normals  .map { it.arrivedTime }.average()).toInt() // 切り上げ
        val busOnlyAveTime = ceil(busOnlies.map { it.arrivedTime }.average()).toInt()
        val noCashAveTime  = ceil(noCashes .map { it.arrivedTime }.average()).toInt()
        val amagasakiAveTime = ceil(amagasakiWalks .map { it.arrivedTime }.average()).toInt()
        val viaNishikitaAveTime = ceil(viaNishikitas .map { it.arrivedTime }.average()).toInt()
        val normalAveCost  = ceil(normals  .map { it.totalCost }.average()).toInt()
        val busOnlyAveCost = ceil(busOnlies.map { it.totalCost }.average()).toInt()

        val strx = view.width-230
        val stry = view.height-140

        g.color = Color.BLACK
        if (people.isNotEmpty()) {
            g.drawString("最速到達者の作戦：" + people.first().strategy, strx, stry)
        }
//        g.drawString("Normal  の数：$normalNum" , strx, stry+12)
        g.drawString("ViaNishikita  の数：$viaNishikitaNum" , strx, stry+12)
        g.drawString("BusOnly       の数：$busOnlyNum", strx, stry+24)
        g.drawString("NoCash        の数：$noCashNum" , strx, stry+36)
        g.drawString("AmagasakiWalk の数：$amagasakiNum" , strx, stry+48)
//        g.drawString("Normal  の平均到着時間：$normalAveTime" , strx, stry+48)
        g.drawString("ViaNishikita  の平均到着時間：$viaNishikitaAveTime" , strx, stry+60)
        g.drawString("BusOnly       の平均到着時間：$busOnlyAveTime", strx, stry+72)
        g.drawString("NoCash        の平均到着時間：$noCashAveTime" , strx, stry+84)
        g.drawString("AmagasakiWalk の平均到着時間：$noCashAveTime" , strx, stry+96)
//        g.drawString("Normal  の平均費用：$normalAveCost" , strx, stry+84)
        g.drawString("BusOnly の平均費用：$busOnlyAveCost", strx, stry+108)

    }


}