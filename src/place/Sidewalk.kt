package place

import agent.Person
import java.awt.Color
import java.awt.Graphics2D

class Sidewalk(override val previous: Node, override val next: Node, override val length: Int) : Way {

    override val cellMaxAgents = 3
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
                for(person in people[index]){
                    if (next is Goal){ // 人がnextに移動
                        next.people.add(person)
                        removingPeople.add(person)
                    } else { // nextはStation
                        val nextSta = next as Station
                        if (nextSta.people.size < nextSta.maxPeopleNum){
                            next.people.add(person)
                            removingPeople.add(person)
                        }
                    }
                }
                for(person in removingPeople){ people[index].remove(person) } // 移動したものを消去
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
        val y1 = previous.point.y.toDouble()
        val y2 = next.point.y.toDouble()

        val X1p = x1
        val Y1p = y1
        val X1m = x1 - width
        val Y1m = y1
        val X2p = x2
        val Y2p = y2
        val X2m = x2 - width
        val Y2m = y2

        g.color = Color.MAGENTA
        g.drawLine(X1p.toInt(), Y1p.toInt(), X1m.toInt(), Y1m.toInt())
        g.drawLine(X2p.toInt(), Y2p.toInt(), X2m.toInt(), Y2m.toInt())
        g.drawLine(X1p.toInt(), Y1p.toInt(), X2p.toInt(), Y2p.toInt())
        g.drawLine(X1m.toInt(), Y1m.toInt(), X2m.toInt(), Y2m.toInt())

        g.color = Color.BLACK
        var count = 0
        for (cell in people){
            count += cell.size
        }
        g.drawString("歩行者の総数: " + count, ((x1+x2)/2 - 120).toInt(), ((y1+y2)/2).toInt())
    }
}