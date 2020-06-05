package place

import java.awt.Graphics2D

interface Place {    // NodeとWay

    fun checkAllAgents(time: Int)
    fun drawSelf(g: Graphics2D)
}