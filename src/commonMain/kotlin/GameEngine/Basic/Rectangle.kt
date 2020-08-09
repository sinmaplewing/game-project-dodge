package GameEngine.Basic

data class Rectangle(
    val leftTop: Point,
    val rightBottom: Point
) {
    fun contains(p: Point) =
        p.x >= leftTop.x && p.x <= rightBottom.x &&
            p.y >= leftTop.y && p.y <= rightBottom.y

    fun collidesWith(c: Circle) =
        (c.center.x - c.radius) > leftTop.x &&
            (c.center.x + c.radius) < rightBottom.x &&
            (c.center.y - c.radius) > leftTop.y &&
            (c.center.y + c.radius) < rightBottom.y
}