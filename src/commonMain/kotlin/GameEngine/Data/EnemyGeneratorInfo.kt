package GameEngine.Data

import GameEngine.Basic.Point

data class EnemyGeneratorInfo(
    val position: Point,
    val duration: Double,
    val probability: Double
)