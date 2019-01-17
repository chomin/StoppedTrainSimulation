import place.*
import java.awt.BorderLayout
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.border.EmptyBorder

class SimulationFrame internal constructor(private val places: ArrayList<Place>): JFrame(), Runnable{

    private val contentPane: JPanel
    private var kissOfDeath = false

    val view = SimulationView(places)

    init {
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        setBounds(100, 100, 1200, 800)
        contentPane = JPanel()
        contentPane.border = EmptyBorder(5, 5, 5, 5)
        setContentPane(contentPane)
        contentPane.layout = BorderLayout(0, 0)



        contentPane.add(view, BorderLayout.CENTER)

        val panel = JPanel()
        contentPane.add(panel, BorderLayout.SOUTH)

    }

    override fun run() {
//        var time = 0
//        while (kissOfDeath){
//
//        }
        for (time in 0 until Main.maxTime){  // 分単位
            // Placeが持つ全エージェントを調べる
            // (goal), 道2つ, 駅, 線路, 駅, 道2つ, startの順で調べる.
            for (place in places.reversed()){

//                if(place is Roadway) continue

                place.checkAllAgents()
                if (time%10 == 0 && place is Node){
                    val node = place as Node
                    when(node.name){
                        "六甲道駅" -> {
                            node.generateCars  (5)
                            node.generateTrains(0)
                        }
                        "塚口（JR）" -> {
                            node.generateCars  (0)
                            node.generateTrains(1)
                        }
                        "塚口（阪急）" -> {
                            node.generateCars  (5)
                        }
                    }
                }
            }

            view.repaint()

            try {
                Thread.sleep(500L)

            } catch (_ex: Exception) {
            }
//            roadway2.checkAllAgents()
//            sidewalk2.checkAllAgents()
//            if (time%10 == 0){
//                goalNearestStation.generateCars(3)
//                goalNearestStation.generateTrains(0)
//            }
//            goalNearestStation.checkAllAgents()
//            railWay1.checkAllAgents()
//            if (time%10 == 0){
//                startNearestStation.generateCars(0)
//                startNearestStation.generateTrains(1)
//            }
//            startNearestStation.checkAllAgents()
//            roadway1.checkAllAgents()
//            sidewalk1.checkAllAgents()
//            if (time%10 == 0){
//                start.generateCars(3)
//            }
//            start.checkAllAgents()

        }
    }
}