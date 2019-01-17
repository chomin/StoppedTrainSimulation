package place

import agent.Person
import java.awt.Color
import java.awt.Graphics2D
import kotlin.math.pow
import kotlin.math.sqrt

class Sidewalk(override val previous: Node, override val next: Node, override val length: Int) : Way {

    override val cellMaxAgents = 10
    var people: Array<ArrayList<Person>>
    val cellNum = length/100

    init {
        addSelfToNodes()
        people = Array(cellNum) { ArrayList<Person>() }
    }

    override fun checkAllAgents() {
        for (i in 0 until cellNum){
            val index = cellNum-i-1 // cellのindex.後ろから探索.
            if(index == cellNum-1) { // 最後のマス
                val removingPeople = ArrayList<Person>()

                if (next is Station) {
                    println("SidewalkからStationへ")
                    println(next.people.size)
                }
                for(person in people[index]){
                    if (next is Goal){ // 人がnextに移動
                        next.people.add(person)
                        removingPeople.add(person)
                    } else { // nextはStation
                        val nextSta = next as Station
                        if (nextSta.people.size < nextSta.maxPeopleNum){
                            nextSta.people.add(person)
                            removingPeople.add(person)
                        }
                    }
                    val t = next.people.size
                }
                for(person in removingPeople){ people[index].remove(person) } // 移動したものを消去
                if (next is Station) {
                    println(next.people.size)
                }



            } else {
                val removingPeople = ArrayList<Person>()
                for(person in people[index]){
                    if (people[index+1].size < cellMaxAgents){ // 人を進める
                        people[index+1].add(person)
                        removingPeople.add(person)
                    }
                }
                for(person in removingPeople){ people[index].remove(person) } // 移動したものを除去
            }
        }
    }

    override fun drawSelf(g: Graphics2D) {

        val width = 10.0
        val x1 = previous.point.x.toDouble()    // 添字1がprevious側
        val x2 = next.point.x.toDouble()
        var y1 = previous.point.y.toDouble()
        var y2 = next.point.y.toDouble()
        y1 += if (y1<y2) Node.radius else -Node.radius
        y2 -= if (y1<y2) Node.radius else -Node.radius
        val wayLength = sqrt((x2-x1).pow(2) + (y2-y1).pow(2)).toInt()

//        val X1p = x1
//        val Y1p = y1
//        val X1m = x1 - width
//        val Y1m = y1
//        val X2p = x2
//        val Y2p = y2
//        val X2m = x2 - width
//        val Y2m = y2
//
//        g.color = Color.MAGENTA
//        g.drawLine(X1p.toInt(), Y1p.toInt(), X1m.toInt(), Y1m.toInt())
//        g.drawLine(X2p.toInt(), Y2p.toInt(), X2m.toInt(), Y2m.toInt())
//        g.drawLine(X1p.toInt(), Y1p.toInt(), X2p.toInt(), Y2p.toInt())
//        g.drawLine(X1m.toInt(), Y1m.toInt(), X2m.toInt(), Y2m.toInt())

        g.color = Color.BLACK
        var count = 0
        for (cell in people){
            count += cell.size
        }
        g.drawString("歩行者の総数: " + count, ((x1+x2)/2 - 130).toInt(), ((y1+y2)/2).toInt())

        // cellの表示
        val cellDotSize = wayLength/people.size
        for ((index, cell) in people.withIndex()){
//            val celly = y1 + index * cellDotSize // TODO: いい感じになるように座標計算
            val celly = if(y1<y2) y1 + index * cellDotSize else y1 - (index+1) * cellDotSize

            val ratio = cell.size/cellMaxAgents.toDouble()
            when{
                ratio < 0.3 -> g.color = Color.BLUE
                ratio > 0.7 -> g.color = Color.RED
                else        -> g.color = Color.ORANGE
            }
            g.fillRect(x1.toInt()-width.toInt(), celly.toInt(), width.toInt(), cellDotSize)
            g.color = Color(0, 125, 125)
            g.drawRect(x1.toInt()-width.toInt(), celly.toInt(), width.toInt(), cellDotSize)
        }
    }
}