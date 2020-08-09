package GameEngine.Data

import GameEngine.Basic.Circle
import GameEngine.Basic.Vector

data class EnemyData(
    var currentPosition: Circle,
    val speed: Double,
    val moveDirection: Vector
)