package Input

import GameEngine.Basic.Vector
import com.soywiz.korev.Key
import com.soywiz.korge.input.Input

class PlayerMoveKeyboardInputHandler(private val input: Input): IPlayerMoveInputHandler {
    private var _currentInputDirection = Vector.ZERO
    override val currentInputDirection: Vector
        get() = _currentInputDirection.normalize()

    override fun update() {
        _currentInputDirection =
            getInputDirection(input) ?: Vector.ZERO
    }

    private fun getInputDirection(input: Input): Vector? {
        val leftPressed = input.keys[Key.A] || input.keys[Key.LEFT]
        val rightPressed = input.keys[Key.D] || input.keys[Key.RIGHT]
        val upPressed = input.keys[Key.W] || input.keys[Key.UP]
        val downPressed = input.keys[Key.S] || input.keys[Key.DOWN]

        if(!leftPressed && !rightPressed && !upPressed && !downPressed) {
            return null
        }

        val x = if(!(leftPressed xor rightPressed)){
            0.0
        } else if(leftPressed) {
            -1.0
        } else {
            1.0
        }

        val y =  if(!(upPressed xor downPressed)){
            0.0
        } else if(upPressed) {
            -1.0
        } else {
            1.0
        }

        return Vector(x, y)
    }
}