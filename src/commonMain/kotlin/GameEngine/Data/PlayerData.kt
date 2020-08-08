package GameEngine.Data

import GameEngine.Basic.Point
import com.soywiz.kds.DoubleStack

data class PlayerData(
    var currentPosition: Point,
    val size: Double,
    val speed: Double
)