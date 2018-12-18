class Goal(): Node{
    override var name = "六甲駅"
    override var ways = ArrayList<Way>()
        get() {
            if (field.isEmpty()) {
                print("waysが空です")
            }
            return field
        }
}