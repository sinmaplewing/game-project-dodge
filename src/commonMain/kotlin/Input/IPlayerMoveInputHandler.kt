package Input

import GameEngine.Basic.Vector

interface IPlayerMoveInputHandler {
    val currentInputDirection: Vector
    fun update()
}