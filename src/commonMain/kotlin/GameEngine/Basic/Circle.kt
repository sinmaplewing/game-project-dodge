package GameEngine.Basic

data class Circle(val center: Point, val radius: Double) {
    fun coerceIn(r: Rectangle): Circle {
        val x = if(center.x - radius < r.leftTop.x) {
            r.leftTop.x + radius
        } else if(center.x + radius > r.rightBottom.x) {
            r.rightBottom.x - radius
        } else {
            center.x
        }

        val y = if(center.y - radius < r.leftTop.y) {
            r.leftTop.y + radius
        } else if(center.y + radius > r.rightBottom.y) {
            r.rightBottom.y - radius
        } else {
            center.y
        }

        return Circle(Point(x, y), radius)
    }
}