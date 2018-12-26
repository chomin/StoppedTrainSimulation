package Agent

class Car(override var agents: ArrayList<Person>, override val speed: Int): Vehicle {
    override val maxPeople = 0
}