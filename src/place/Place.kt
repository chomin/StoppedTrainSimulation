package place

import java.awt.Graphics2D

interface Place{    // NodeとWay

    fun checkAllAgents()
    fun drawSelf(g: Graphics2D)
}