package place

import java.awt.Graphics2D

interface Place{    // NodeとWay

    fun checkAllAgents()
    fun checkAgent()
    fun drawSelf(g: Graphics2D)
}