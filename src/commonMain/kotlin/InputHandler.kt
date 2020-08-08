import GameEngine.Basic.Vector
import GameEngine.DodgeGameEngine
import com.soywiz.korev.Key
import com.soywiz.korge.input.Input

class InputHandler(private val input: Input) {
    fun getCurrentInputDirection(): Vector {
        val leftPressed = input.keys[Key.A] || input.keys[Key.LEFT]
        val rightPressed = input.keys[Key.D] || input.keys[Key.RIGHT]
        val upPressed = input.keys[Key.W] || input.keys[Key.UP]
        val downPressed = input.keys[Key.S] || input.keys[Key.DOWN]

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