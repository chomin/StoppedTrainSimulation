package place


import agent.Person
import agent.Train
import java.awt.Color
import java.awt.Graphics2D
import kotlin.math.pow
import kotlin.math.sqrt

class RailWay(override val previous: Node, override val next: Node, override val meters: Int, val trainFreq: Int) :
    Way {

    override val cellMaxAgents = 1
    override var isHorizontal = true
    val cellNum: Int = meters/1000
    var trains: Array<ArrayList<Train>>
    val prevStation = previous as? Station

    val nextStation = next as? Station


    init {
        addSelfToNodes()

//        if (next !is Station || previous !is Station){
//            println("駅に結びついていません!")
//        }

        trains = Array(cellNum) { ArrayList<Train>() }
    }

    override fun checkAllAgents(time: Int) {
        for (i in 0 until cellNum){
            val index = cellNum-i-1 // cellのindex.後ろから探索.
            if(index == cellNum-1) { // 最後のマス
                val removingTrains = ArrayList<Train>()
                for(train in trains[index]){
                    if (nextStation != null ){
                        if (nextStation.waitingTrains.size < nextStation.maxTrainNum){ // 電車を進める
                            nextStation.waitingTrains.add(Pair(this, train))    // TODO: 行き先に応じたRailWay
                            removingTrains.add(train)
                        }
                    }else {
                        for (person in train.people) {
                            next.people.add(person)
                            person.arrivedTime = time
                        }
                        removingTrains.add(train)
                    }

                }
                for(train in removingTrains){ trains[index].remove(train) } // 移動したものを除去
            } else {
                val removingTrains = ArrayList<Train>()
                for(train in trains[index]){
                    if (trains[index+1].size < cellMaxAgents){ // 電車を進める
                        trains[index+1].add(train)
                        removingTrains.add(train)
                    }
                }
                for(train in removingTrains){ trains[index].remove(train) } // 移動したものを除去
            }



        }
    }

    override fun drawSelf(g: Graphics2D) {
        val halfWidth = 7.0
        val x1 =
            if(isHorizontal) previous.point.x.toDouble() + Node.radius
            else previous.point.x.toDouble() // 添字1がprevious側
        val x2 =
            if(isHorizontal) next.point.x.toDouble() - Node.radius
            else next.point.x.toDouble()
        val y1 = previous.point.y.toDouble()
        val y2 = next.point.y.toDouble()
        val wayLength = sqrt((x2-x1).pow(2) + (y2-y1).pow(2)).toInt()
//        if (y1==y2) {
//            println("高さ同じTODO")
//        }


//        val alpha = (x2-x1)/(y1-y2) // 道に直交する直線の傾き
//
//        // ２次方程式の係数
//        val a12 = 1 + alpha.pow(2)  // 12共通
//        val b1 = -2*x1 - 2*alpha.pow(2)*x1
//        val b2 = -2*x2 - 2*alpha.pow(2)*x2
//        val c1 = x1.pow(2) + alpha.pow(2)*x1.pow(2) - half_width.pow(2)
//        val c2 = x2.pow(2) + alpha.pow(2)*x2.pow(2) - half_width.pow(2)
//
//        val X1p = (b1.pow(2) + sqrt(b1.pow(2) - 4*a12*c1)) / 2*a12
//        val Y1p = alpha*X1p - alpha*x1 + y1
//        val X1m = (b1.pow(2) - sqrt(b1.pow(2) - 4*a12*c1)) / 2*a12
//        val Y1m = alpha*X1m - alpha*x1 + y1
//        val X2p = (b2.pow(2) + sqrt(b2.pow(2) - 4*a12*c2)) / 2*a12
//        val Y2p = alpha*X2p - alpha*x2 + y2
//        val X2m = (b2.pow(2) - sqrt(b2.pow(2) - 4*a12*c2)) / 2*a12
//        val Y2m = alpha*X2m - alpha*x2 + y2
//        val X1p = x1
//        val Y1p = y1 + halfWidth
//        val X1m = x1
//        val Y1m = y1 - halfWidth
//        val X2p = x2
//        val Y2p = y2 + halfWidth
//        val X2m = x2
//        val Y2m = y2 - halfWidth
//
//        g.color = Color.BLACK
//        g.drawLine(X1p.toInt(), Y1p.toInt(), X1m.toInt(), Y1m.toInt())
//        g.drawLine(X2p.toInt(), Y2p.toInt(), X2m.toInt(), Y2m.toInt())
//        g.drawLine(X1p.toInt(), Y1p.toInt(), X2p.toInt(), Y2p.toInt())
//        g.drawLine(X1m.toInt(), Y1m.toInt(), X2m.toInt(), Y2m.toInt())

        var count = 0
        for (cell in trains){
            count += cell.size
        }
        g.color = Color.BLACK
        g.drawString("電車の総数: $count", ((x1+x2)/2).toInt(), ((y1+y2)/2 - 20).toInt())

        // cellの表示
        val cellDotSize = wayLength/trains.size
        for ((index, cell) in trains.withIndex()){
            val cellx = x1 + index * cellDotSize // TODO: いい感じになるように座標計算

            val ratio = cell.size/cellMaxAgents.toDouble()
            when{
                ratio < 0.3 -> g.color = Color.BLUE
                ratio > 0.7 -> g.color = Color.RED
                else        -> g.color = Color.ORANGE
            }
            g.fillRect(cellx.toInt(), y1.toInt()-10, cellDotSize, 20)
            g.color = Color.BLACK
            g.drawRect(cellx.toInt(), y1.toInt()-10, cellDotSize, 20)
        }

    }
}