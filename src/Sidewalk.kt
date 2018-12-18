class Sidewalk(override val previous: Node, override val next: Node, override val length: Int) : Way{
    var agents = ArrayList<Person>()
    init {
        addSelfToNodes()
    }
}