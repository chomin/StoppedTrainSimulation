package Agent

class Train(override var agents: ArrayList<Person>, override val speed: Int) : Vehicle { // 8両編成とする
    override val maxPeople = 250*8 // 競合路線から乗れるのは1駅あたり250人とする

}