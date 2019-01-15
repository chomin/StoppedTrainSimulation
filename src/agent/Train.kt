package agent

enum class TrainState {
    GotOn(), Running(), GotOff()
}

class Train(override var people: ArrayList<Person>) : Vehicle { // 8両編成とする

    companion object {
        val maxPeople = 250*8 // 競合路線から乗れるのは1駅あたり250人とする
        val speed = 30
    }

    var state = TrainState.GotOn

}