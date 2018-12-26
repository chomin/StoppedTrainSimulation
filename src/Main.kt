import Agent.Person
import Agent.Strategy
import Place.*
import java.util.*

fun main(args: Array<String>) {

    val rand = Random()

    val maxTime = 180   // とりあえず3時間
    val people = ArrayList<Person>()   // 250*8が止まった駅からスタート
    for (i in 0 until 250*8){
        people.add(Person(rand.nextInt(11), rand.nextInt(11), rand.nextInt(11), Strategy.Normal))
    }
    // まずノードを生成し、道を生成した後ノードに道情報をセット(道のイニシャライザでセット)。
    val start = Start()
    val goal  = Goal()
    val startNearestStation = Station("塚口（JR）")
    val goalNearestStation  = Station("六甲道")


    val taxiFreq  = 0.2  // １台あたりの時間(分)
    val trainFreq = 7

    val railWay1  = RailWay(startNearestStation, goalNearestStation, 20700, trainFreq)
    val sidewalk1 = Sidewalk(start, startNearestStation, 1000)
    val sidewalk2 = Sidewalk(goalNearestStation, goal, 750)
    val roadway1  = Roadway(start, startNearestStation, 1000, 12)
    val roadway2  = Roadway(goalNearestStation, goal, 750, 3)

    // タクシーは一定時間ごとに、バスと電車はダイヤ(できればcsvとかを使う)に沿って生成される。(朝８時からスタート)
    // とりあえず最初は一定時間ごとに生成

    for (time in 0 until maxTime){  // 分単位

    }

}


