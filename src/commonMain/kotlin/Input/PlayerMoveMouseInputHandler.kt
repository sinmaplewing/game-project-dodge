package Input

import GameEngine.Basic.toPoint
import GameEngine.Basic.Point
import GameEngine.Basic.Vector
import com.soywiz.korge.input.Input

class PlayerMoveMouseInputHandler(private val input: Input): IPlayerMoveInputHandler {
    private var _currentInputDirection = Vector.ZERO
    override val currentInputDirection: Vector
        get() = _currentInputDirection.normalize()

    private var hasClicked = false
    private var firstClickedPosition = Point.ZERO

    override fun update() {
        _currentInputDirection =
                getInputDirection(input) ?: Vector.ZERO
    }

    private fun getInputDirection(input: Input): Vector? {
        val isCurrentClicked = isClicked(input)
        if (!isCurrentClicked) {
            hasClicked = isCurrentClicked
            firstClickedPosition = Point.ZERO
            return null
        }

        if (!hasClicked) {
            hasClicked = isCurrentClicked
            firstClickedPosition = getCurrentClickedPosition(input)
            return Vector.ZERO
        }

        val currentClickedPosition = getCurrentClickedPosition(input)
        return currentClickedPosition - firstClickedPosition
    }

    private fun getCurrentClickedPosition(input: Input) =
            if (isClicked(input)){
                input.mouse.toPoint()
            } else {
                Point.ZERO
            }

    private fun isClicked(input: Input) =
            input.mouseButtons > 0

}