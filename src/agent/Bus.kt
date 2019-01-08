package agent

class Bus(override var people: ArrayList<Person>) : Vehicle {
    companion object {
        val maxPeople = 50
        val speed = 10
    }

}