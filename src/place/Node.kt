package place

import java.awt.Point

interface Node: Place{
    var name: String
    var ways: ArrayList<Way>
    var point: Point
}