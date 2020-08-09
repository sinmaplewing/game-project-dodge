package GameEngine.Basic

import kotlin.math.*

data class Rectangle(
    val leftTop: Point,
    val rightBottom: Point
) {
    fun contains(p: Point) =
        p.x >= leftTop.x && p.x <= rightBottom.x &&
        p.y >= leftTop.y && p.y <= rightBottom.y
}