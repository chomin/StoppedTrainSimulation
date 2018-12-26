package Place

interface Way{   // Place.RailWay, Place.Roadway, Place.Sidewalk
    val previous: Node
    val next: Node
    val length: Int
    fun addSelfToNodes(){
        previous.ways.add(this)
        next.ways.add(this)
    }
}