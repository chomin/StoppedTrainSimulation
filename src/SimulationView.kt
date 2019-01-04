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

//        for (point in points) {
//            when {
//                points.indexOf(point) == 0               -> g.color = Color.CYAN
//                points.indexOf(point) == points.size - 1 -> g.color = Color.MAGENTA
//                else                                     -> g.color = Color.DARK_GRAY
//            }
//            g.fill3DRect(point.x, point.y, pointSize, pointSize, true)
//        }
//
//        if (!way.isEmpty()) {
//
//            g.color = Color.ORANGE
//
//            val xArray = IntArray(way.size)
//            val yArray = IntArray(way.size)
//
//            for (i in way.indices) {
//                val point = way[i]
//                xArray[i] = point.x
//                yArray[i] = point.y
//            }
//            g.drawPolyline(xArray, yArray, way.size)
//        }

    }

//    internal fun reset() {
//        val textArea = SearchNodeFrame.textArea
//        points.clear()
//        way.clear()
//        repaint()
//        textArea.text = "リセットしました。"
//    }
//
//    internal fun solve() {
//        val textArea = SearchNodeFrame.textArea
//        if (points.size < 2) {
//            val textArea = SearchNodeFrame.textArea
//            textArea.text = "点が少なすぎます。もっと点を打ち込んでください。"
//            textArea.foreground = Color.RED
//        } else {
//            val result = solver.solve(points)
//            if (result.goal.priority < 0) {
//                textArea.text = "到達不可です。点を増やすか、Solver.OneHopを変更してください。OneHop: " + Solver.OneHop
//            } else {
//                textArea.text = "最短経路長: " + result.goal.priority
//            }
//            textArea.foreground = Color.BLACK
//            this.way = result.way
//        }
//        repaint()
//    }
//
//    internal fun save() {
//        FileManager.save(SearchNodeFrameData(points, way))
//
//    }
//
//    internal fun load() {
//
//        val textArea = SearchNodeFrame.textArea
//
//        try {
//            val data = FileManager.load()
//            points = data.points
//            way = data.way
//            repaint()
//            textArea.foreground = Color.BLACK
//            textArea.text = "ロードしました。"
//
//        }catch (e: Exception){
//            textArea.foreground = Color.RED
//            textArea.text = "ファイルの読み込みに失敗しました。"
//            print(e)
//        }
//    }

//    internal fun mouseReleased() {
//        repaint()
//    }
//
//    internal fun mousePressed(point: Point) {
//        points.add(point)
//    }

}