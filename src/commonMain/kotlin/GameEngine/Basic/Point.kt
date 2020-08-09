package GameEngine.Basic

data class Point(val x: Double, val y: Double) {
    companion object{
        val ZERO = Point(0.0, 0.0)
    }

    operator fun plus(v: Vector): Point =
        Point(x + v.x, y + v.y)

    operator fun minus(p: Point): Vector =
        Vector(x - p.x, y - p.y)

    fun coerceIn(r: Rectangle) =
        Point(
            x.coerceIn(r.leftTop.x, r.rightBottom.x),
            y.coerceIn(r.leftTop.y, r.rightBottom.y)
        )
}

fun com.soywiz.korma.geom.Point.toPoint(): Point =
    Point(x, y)