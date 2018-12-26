package Agent

class Bus(override var agents: ArrayList<Person>, override val speed: Int) : Vehicle {
    override val maxPeople = 50

}