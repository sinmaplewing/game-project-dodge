package GameEngine.Data

import GameEngine.Basic.Point

data class EnemyInfo(
    val position: Point,
    val duration: Double,
    val probability: Double
)