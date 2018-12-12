interface Way: Place{  // ノードで表現
    val next: Node
    val previous: Node?
}