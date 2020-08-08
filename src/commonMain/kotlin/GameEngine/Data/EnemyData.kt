package GameEngine.Data

import GameEngine.Basic.Point

data class EnemyData(
    var currentPosition: Point,
    val size: Double,
    val speed: Double
)