package Place

class RailWay(override val previous: Node, override val next: Node, override val length: Int, val trainFreq: Int) :
    Way {
    init {
        addSelfToNodes()
    }
}