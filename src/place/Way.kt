package place

interface Way: Place{   // place.RailWay, place.Roadway, place.Sidewalk
    val previous: Node
    val next: Node
    val length: Int
    var cellsAgentNum: Array<Int>    //
    val cellMaxAgents: Int

    fun addSelfToNodes(){
        previous.ways.add(this)
        next.ways.add(this)
    }
}