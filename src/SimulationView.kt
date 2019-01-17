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

class SimulationView internal constructor(private val places: ArrayList<Place>) : JPanel(){

    public override fun paintComponent(g0: Graphics) {
        val g = g0 as Graphics2D
        g.background = Color.LIGHT_GRAY
        g.clearRect(0, 0, width, height)

        for (place in places){
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