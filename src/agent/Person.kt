package agent

import place.Station
import java.awt.Point

enum class Strategy{
    Normal()
}

class Person(val money: Int, val vitarity: Int, val cleverness: Int, val strategy: Strategy): Agent{
    var current = Station("", Point(0, 0))

}