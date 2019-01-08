package place


import java.awt.Color
import java.awt.Graphics2D
import kotlin.math.sqrt
import kotlin.math.pow

class RailWay(override val previous: Node, override val next: Node, override val length: Int, val trainFreq: Int) :
    Way {
    override lateinit var cells: Array<Double>
    init {
        addSelfToNodes()
        val cellNum: Int = length/1000
        cells = Array(cellNum) { 0.0 }

    }

    override fun drawSelf(g: Graphics2D) {
        val half_width = 7.0
        val x1 = previous.point.x.toDouble()    // 添字1がprevious側
        val x2 = next.point.x.toDouble()
        val y1 = previous.point.y.toDouble()
        val y2 = next.point.y.toDouble()
        if (y1==y2) {
            println("高さ同じTODO")
        }
        if (x1==x2){
            println("x同じTODO")
        }

        val alpha = (x2-x1)/(y1-y2) // 道に直交する直線の傾き

        // ２次方程式の係数
        val a12 = 1 + alpha.pow(2)  // 12共通
        val b1 = -2*x1 - 2*alpha.pow(2)*x1
        val b2 = -2*x2 - 2*alpha.pow(2)*x2
        val c1 = x1.pow(2) + alpha.pow(2)*x1.pow(2) - alpha.pow(2)
        val c2 = x2.pow(2) + alpha.pow(2)*x2.pow(2) - alpha.pow(2)

        val X1p = (b1.pow(2) + sqrt(b1.pow(2) - 4*a12*c1)) / 2*a12
        val Y1p = alpha*X1p - alpha*x1 + y1
        val X1m = (b1.pow(2) - sqrt(b1.pow(2) - 4*a12*c1)) / 2*a12
        val Y1m = alpha*X1m - alpha*x1 + y1
        val X2p = (b2.pow(2) + sqrt(b2.pow(2) - 4*a12*c2)) / 2*a12
        val Y2p = alpha*X2p - alpha*x2 + y2
        val X2m = (b2.pow(2) - sqrt(b2.pow(2) - 4*a12*c2)) / 2*a12
        val Y2m = alpha*X2m - alpha*x2 + y2

        g.color = Color.BLACK
        g.drawLine(X1p.toInt(), Y1p.toInt(), X1m.toInt(), Y1m.toInt())
        g.drawLine(X2p.toInt(), Y2p.toInt(), X2m.toInt(), Y2m.toInt())
        g.drawLine(X1p.toInt(), Y1p.toInt(), X2p.toInt(), Y2p.toInt())
        g.drawLine(X1m.toInt(), Y1m.toInt(), X2m.toInt(), Y2m.toInt())
    }
}