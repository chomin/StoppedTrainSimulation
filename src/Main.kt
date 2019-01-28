import agent.Person
import agent.Strategy
import place.*
import java.util.*
import java.awt.Point
import kotlin.collections.ArrayList

object Main{

    val maxTime = 1000

    @JvmStatic
    fun main(args: Array<String>) {

        val rand = Random()

        val people = ArrayList<Person>()   // 250*8が止まった駅からスタート
        val peopleNum = 250*10
        for (i in 0 until peopleNum){
            when(i%10){
//                0 -> people.add(Person(rand.nextInt(11), rand.nextInt(11), rand.nextInt(11), Strategy.Normal))
                0,1,2,3 -> people.add(Person(rand.nextInt(11), rand.nextInt(11), rand.nextInt(11), Strategy.BusOnly))
                4,5 -> people.add(Person(rand.nextInt(11), rand.nextInt(11), rand.nextInt(11), Strategy.NoCash))
                6,7 ->people.add(Person(rand.nextInt(11), rand.nextInt(11), rand.nextInt(11), Strategy.AmagasakiWalk))
                8,9 -> people.add(Person(rand.nextInt(11), rand.nextInt(11), rand.nextInt(11), Strategy.ViaNishikita))
            }

        }
        // まずノードを生成し、道を生成した後ノードに道情報をセット(道のイニシャライザでセット)。
        val places = ArrayList<Place>()
        val start = Start(Point(250, 200), people)

        val goal  = Goal(Point(800, 200))

        val startNearestStation = Station("塚口（JR）", Point(100, 500))
        val goalNearestStation  = Station("六甲道駅"  , Point(800, 500))
        val nishikitaSta = Station("西宮北口", Point(500, 200))
        nishikitaSta.startTime = 30
        val amagasakiSta = Station("尼崎", Point(350, 500))



//        val taxiFreq  = 0.2  // １台あたりの時間(分)
        val railWay1  = RailWay(startNearestStation, amagasakiSta, 2500, 7)
        val railWay2 = RailWay(amagasakiSta, goalNearestStation, 18200, 4)
        val railWay11  = RailWay(nishikitaSta, goal, 11800, 7)
        val sidewalk1 = Sidewalk(start, startNearestStation, 1000)
        val sidewalk2 = Sidewalk(goalNearestStation, goal, 750)
        val sidewalk3 = Sidewalk(start, amagasakiSta, 3600)
        sidewalk3.isDownStr = true
        val roadway1  = Roadway(start, startNearestStation, 1000, 12, 210)
        val roadway2  = Roadway(goalNearestStation, goal, 750, 3, 210)
        val roadway3  = Roadway(start, amagasakiSta, 3600, 20, 210)
        val sidewalk11 = Sidewalk(start, nishikitaSta, 6000)
        sidewalk11.isHorizontal = true

        places.add(start)
        places.add(roadway1)
        places.add(sidewalk1)
        places.add(roadway3)
        places.add(sidewalk3)
        places.add(sidewalk11)
        places.add(startNearestStation)
        places.add(railWay1)
        places.add(nishikitaSta)
        places.add(amagasakiSta)
        places.add(railWay2)
        places.add(railWay11)
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




