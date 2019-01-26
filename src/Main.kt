import agent.Person
import agent.Strategy
import place.*
import java.util.*
import java.awt.EventQueue
import java.awt.Point
import kotlin.collections.ArrayList

object Main{

    val maxTime = 1000

    @JvmStatic
    fun main(args: Array<String>) {

        val rand = Random()

        val people = ArrayList<Person>()   // 250*8が止まった駅からスタート
        val peopleNum = 250*8
        for (i in 0 until peopleNum){
            when(i%3){
                0 -> people.add(Person(rand.nextInt(11), rand.nextInt(11), rand.nextInt(11), Strategy.Normal))
                1 -> people.add(Person(rand.nextInt(11), rand.nextInt(11), rand.nextInt(11), Strategy.BusOnly))
                2 -> people.add(Person(rand.nextInt(11), rand.nextInt(11), rand.nextInt(11), Strategy.NoCash))
            }

        }
        // まずノードを生成し、道を生成した後ノードに道情報をセット(道のイニシャライザでセット)。
        val places = ArrayList<Place>()
        val start = Start(Point(200, 200), people)

        val goal  = Goal(Point(800, 200))

        val startNearestStation = Station("塚口（JR）", Point(200, 600))

        val goalNearestStation  = Station("六甲道駅"  , Point(800, 600))



//        val taxiFreq  = 0.2  // １台あたりの時間(分)
        val trainFreq = 7
        val railWay1  = RailWay(startNearestStation, goalNearestStation, 20700, trainFreq)
        val sidewalk1 = Sidewalk(start, startNearestStation, 1000)
        val sidewalk2 = Sidewalk(goalNearestStation, goal, 750)
        val roadway1  = Roadway(start, startNearestStation, 1000, 12, 210)
        val roadway2  = Roadway(goalNearestStation, goal, 750, 3, 210)

        places.add(start)
        places.add(roadway1)
        places.add(sidewalk1)
        places.add(startNearestStation)
        places.add(railWay1)
        places.add(goalNearestStation)
        places.add(roadway2)
        places.add(sidewalk2)
        places.add(goal)



        // タクシーは一定時間ごとに、バスと電車はダイヤ(できればcsvとかを使う)に沿って生成される。(朝８時からスタート)
        // とりあえず最初は一定時間ごとに生成


        // 可視化
//        EventQueue.invokeLater {
            try {
                val frame = SimulationFrame(places, peopleNum)
                frame.isVisible = true
                frame.run()
            } catch (e: Exception) {
                e.printStackTrace()
            }
//        }
    }
}




