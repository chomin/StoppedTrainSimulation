class Roadway(override val previous: Node, override val next: Node, override val length: Int, val busFreq: Int) : Way {
    var agents = ArrayList<Vehicle>()
    init {
        addSelfToNodes()
    }
}