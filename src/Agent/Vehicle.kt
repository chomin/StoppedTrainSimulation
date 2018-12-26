package Agent

interface Vehicle {
    var agents: ArrayList<Person>
    val maxPeople: Int
    val speed: Int
}