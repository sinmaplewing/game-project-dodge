package GameEngine

import GameEngine.Basic.Circle
import GameEngine.Basic.Vector
import GameEngine.Data.GameStateModel

class DodgeGameEngine(
    val gameStateModel: GameStateModel
) {
    fun update(deltaTime: Double, inputDirection: Vector) {
        val player = gameStateModel.player
        val nextPosition = (player.currentPosition.center + inputDirection.normalize() * player.speed * deltaTime)
        player.currentPosition = Circle(nextPosition, player.currentPosition.radius)
            .coerceIn(gameStateModel.gamePlayContext.gamePlayRange)
    }
}