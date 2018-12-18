class Start() : Node{
    override var name = "塚口（阪急）"
    override var ways = ArrayList<Way>()
        get() {
            if (field.isEmpty()) {
                print("waysが空です")
            }
            return field
        }
}