package Place

class Station(override var name: String): Node {
    override var ways = ArrayList<Way>()
        get() {
            if (field.isEmpty()) {
                print("waysが空です")
            }
            return field
        }
}