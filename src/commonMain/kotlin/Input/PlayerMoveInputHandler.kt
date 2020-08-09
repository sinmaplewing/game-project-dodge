package Input

import GameEngine.Basic.toPoint
import GameEngine.Basic.Point
import GameEngine.Basic.Vector
import com.soywiz.korge.input.Input

class PlayerMoveInputHandler(
    private val inputHandlers: Array<IPlayerMoveInputHandler>)
    : IPlayerMoveInputHandler {

    override val currentInputDirection: Vector
        get() = inputHandlers.fold(Vector.ZERO) { acc, it ->
            acc + it.currentInputDirection
        }.normalize()

    override fun update() = inputHandlers.forEach { it.update() }
}