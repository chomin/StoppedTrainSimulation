enum class Strategy{
    Normal()
}

class Person(val money: Int, val vitarity: Int, val cleverness: Int, val strategy: Strategy): Agent{
    var current = Station(false)

}