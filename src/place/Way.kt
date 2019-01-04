package place

interface Way: Place{   // place.RailWay, place.Roadway, place.Sidewalk
    val previous: Node
    val next: Node
    val length: Int
    var cells: Array<Double>
    fun addSelfToNodes(){
        previous.ways.add(this)
        next.ways.add(this)
    }
}