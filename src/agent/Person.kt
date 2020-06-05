package agent

import place.Station
import java.awt.Point

enum class Strategy {
    Normal(), BusOnly(), NoCash(), ViaNishikita(), AmagasakiWalk()
}

class Person(val money: Int, val vitarity: Int, val cleverness: Int, val strategy: Strategy) : Agent {

    var arrivedTime = 0
    var totalCost = 0
}