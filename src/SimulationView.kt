import place.Goal
import place.Place
import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.Point
import java.io.File
import java.util.*
import javax.swing.JPanel
import javax.swing.JTextArea
import kotlin.collections.ArrayList

class SimulationView internal constructor(private val places: ArrayList<Place>) : JPanel() {

    var time = 0

    init {
        (places.first { it is Goal } as Goal).view = this
    }

    public override fun paintComponent(g0: Graphics) {
        val g = g0 as Graphics2D
        g.background = Color.LIGHT_GRAY
        g.clearRect(0, 0, width, height)

        g.drawString("開始からの時間：$time", width / 2, font.size)

        for (place in places) {
            place.drawSelf(g)
        }

//        println("paint")
    }

    override fun update(g: Graphics?) {
        super.update(g)
        if (g != null) {
            paintComponent(g)
        }
    }

}