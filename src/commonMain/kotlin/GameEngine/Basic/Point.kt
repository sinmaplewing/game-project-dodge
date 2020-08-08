package GameEngine.Basic

data class Point(val x: Double, val y: Double) {
    operator fun plus(v: Vector): Point =
        Point(x + v.x, y + v.y)
}