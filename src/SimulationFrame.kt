import place.*
import java.awt.BorderLayout
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.border.EmptyBorder

class SimulationFrame internal constructor(private val places: ArrayList<Place>): JFrame(), ActionListener, Runnable{

    private val contentPane: JPanel
    private var kissOfDeath = false
    private var time = 0

    private var t: Thread? = null

    val view = SimulationView(places)
    val button = JButton("start")

    init {
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        setBounds(100, 100, 1200, 800)
        contentPane = JPanel()
        contentPane.border = EmptyBorder(5, 5, 5, 5)
        setContentPane(contentPane)
        contentPane.layout = BorderLayout(0, 0)

        contentPane.add(view, BorderLayout.CENTER)


        val panel = JPanel()
        panel.add(button)

        contentPane.add(panel, BorderLayout.SOUTH)

        button.addActionListener(this)

    }

    override fun actionPerformed(e: ActionEvent?) {
        if (e?.source == button) {
            when(kissOfDeath){
                true -> {
                    kissOfDeath = false
                    button.text = "start"
                }
                false -> {
                    kissOfDeath = true
                    button.text = "stop"
                    t = Thread(this)
                    t!!.start()
                }
            }
        }
    }

    override fun run() {


        while (kissOfDeath && time < Main.maxTime){ // 分単位

            // Placeが持つ全エージェントを調べる
            // (goal), 道2つ, 駅, 線路, 駅, 道2つ, startの順で調べる.
            for (place in places.reversed()){

//                if(place is Roadway) continue

                // 徒歩は分速80m 5回に１回休む　車は分速500m(空いているとき) 鉄道は1000m
                if(time%5 == 0 && place is Sidewalk){ continue }
                else if (place is Roadway) {
                    for (i in 0 until 4){
                        place.checkAllAgents()
                    }
                }
                place.checkAllAgents()


                // TODO: 道のfreqに応じて電車やバスを生成



                if (time%10 == 0 && place is Node){
                    when(place.name){
//                        "六甲道駅" -> {
//                            place.generateCars  (5, time)
//                            place.generateTrains(0, time)
//                        }
//                        "塚口（JR）" -> {
//                            place.generateCars  (0)
//                            place.generateTrains(1)
//                        }
//                        "塚口（阪急）" -> {
//                            place.generateCars  (5)
                        }
                    }
                }
            }

            view.time = time
            view.repaint()

            try {
                Thread.sleep(100L)

            } catch (_ex: Exception) {
            }

            time++
        }
//        for (time in 0 until Main.maxTime){
//
//
//        }
    }
}