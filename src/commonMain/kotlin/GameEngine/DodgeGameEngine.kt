package GameEngine

import GameEngine.Basic.Vector
import GameEngine.Data.GameStateModel

class DodgeGameEngine(
    val gameStateModel: GameStateModel
) {
    fun update(inputDirection: Vector) {
        val player = gameStateModel.player
        gameStateModel.player.currentPosition =
            player.currentPosition + inputDirection.normalize() * player.speed
    }
}