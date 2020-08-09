package GameEngine.Basic

import kotlin.math.*

data class Vector(val x: Double, val y: Double) {
    companion object {
        val ZERO: Vector = Vector(0.0, 0.0)
    }

    operator fun plus(v: Vector): Vector =
        Vector(x + v.x, y + v.y)

    operator fun times(n: Double): Vector =
        Vector(x * n, y * n)

    operator fun div(n: Double): Vector =
        Vector(x / n, y / n)

    fun normalize(): Vector =
        if (x == 0.0 && y == 0.0) ZERO
        else this / hypot(x, y)
}