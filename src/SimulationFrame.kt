import place.Place
import java.awt.BorderLayout
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.border.EmptyBorder

class SimulationFrame internal constructor(private val places: ArrayList<Place>): JFrame(){

    private val contentPane: JPanel
    companion object {
//        val textArea = JTextArea()
    }


    init {
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        setBounds(100, 100, 1200, 800)
        contentPane = JPanel()
        contentPane.border = EmptyBorder(5, 5, 5, 5)
        setContentPane(contentPane)
        contentPane.layout = BorderLayout(0, 0)

        val view = SimulationView(places)
//        pointsView.addMouseListener(object : MouseAdapter() {
//            override fun mousePressed(e: MouseEvent?) {
//                pointsView.mousePressed(e!!.point)
//            }
//
//            override fun mouseReleased(e: MouseEvent?) {
//                pointsView.mouseReleased()
//            }
//        })
        contentPane.add(view, BorderLayout.CENTER)

        val panel = JPanel()
        contentPane.add(panel, BorderLayout.SOUTH)

//        val resetButton = JButton("RESET")
//        resetButton.addActionListener { view.reset() }
//        panel.add(resetButton)
//
//        val solveButton = JButton("SOLVE")
//        solveButton.addActionListener { view.solve() }
//        panel.add(solveButton)
//
//        val saveButton = JButton("SAVE")
//        saveButton.addActionListener { view.save() }
//        panel.add(saveButton)
//
//        val loadButton = JButton("LOAD")
//        loadButton.addActionListener { view.load() }
//        panel.add(loadButton)
//
//
//        contentPane.add(textArea, BorderLayout.NORTH)
//        textArea.columns = 20
//        textArea.lineWrap = true
    }


}