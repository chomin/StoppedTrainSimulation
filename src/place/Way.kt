package place

interface Way: Place{   // place.RailWay, place.Roadway, place.Sidewalk
    val previous: Node
    val next: Node
    val meters: Int
    val cellMaxAgents: Int
    var isHorizontal: Boolean

    fun addSelfToNodes(){
        previous.ways.add(this)
        if (previous is Station){
            val sta = previous as Station
            sta.nexts.add(this)
        }
        next.ways.add(this)
        if (next is Station){
            val sta = next as Station
            sta.prevs.add(this)
        }
    }
}