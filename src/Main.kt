import agent.Person
import agent.Strategy
import place.*
import java.util.*
import java.awt.EventQueue
import java.awt.Point
import kotlin.collections.ArrayList

object Main{

    @JvmStatic
    fun main(args: Array<String>) {

        val rand = Random()

        val maxTime = 180   // とりあえず3時間
        val people = ArrayList<Person>()   // 250*8が止まった駅からスタート
        for (i in 0 until 250*8){
            people.add(Person(rand.nextInt(11), rand.nextInt(11), rand.nextInt(11), Strategy.Normal))
        }
        // まずノードを生成し、道を生成した後ノードに道情報をセット(道のイニシャライザでセット)。
        val places = ArrayList<Place>()
        val start = Start(Point(200, 400))
        places.add(start)
        val goal  = Goal(Point(1000, 420))
        places.add(goal)
        val startNearestStation = Station("塚口（JR）", Point(200, 600))
        places.add(startNearestStation)
        val goalNearestStation  = Station("六甲道駅", Point(1000, 650))
        places.add(goalNearestStation)


        val taxiFreq  = 0.2  // １台あたりの時間(分)
        val trainFreq = 7

        val railWay1  = RailWay(startNearestStation, goalNearestStation, 20700, trainFreq)
        places.add(railWay1)
        val sidewalk1 = Sidewalk(start, startNearestStation, 1000)
        places.add(sidewalk1)
        val sidewalk2 = Sidewalk(goalNearestStation, goal, 750)
        places.add(sidewalk2)
        val roadway1  = Roadway(start, startNearestStation, 1000, 12)
        places.add(roadway1)
        val roadway2  = Roadway(goalNearestStation, goal, 750, 3)
        places.add(roadway2)



        // タクシーは一定時間ごとに、バスと電車はダイヤ(できればcsvとかを使う)に沿って生成される。(朝８時からスタート)
        // とりあえず最初は一定時間ごとに生成

        for (time in 0 until maxTime){  // 分単位

        }

        // 可視化
        EventQueue.invokeLater {
            try {
                val frame = SimulationFrame(places)
                frame.isVisible = true
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}




