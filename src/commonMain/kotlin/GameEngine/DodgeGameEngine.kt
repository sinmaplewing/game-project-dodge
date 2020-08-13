package GameEngine

import GameEngine.Basic.Circle
import GameEngine.Basic.Vector
import GameEngine.Data.ActionType
import GameEngine.Data.GameStateModel
import kotlin.math.absoluteValue

class DodgeGameEngine(
    private val gameStateModel: GameStateModel
) {
    private val enemies: Array<Enemy> =
        gameStateModel.stageInfo.enemiesData
            .map {
                Enemy(gameStateModel, it)
            }.toTypedArray()


    fun update(deltaTime: Double, inputDirection: Vector) {
        updateEnemy(deltaTime)
        updatePlayer(deltaTime, inputDirection)

        if (gameStateModel.bullets.any {
                it.currentPosition.collidesWith(
                    gameStateModel.player.currentPosition
                )
        }) {
            gameStateModel.player.isDead = true
        }
    }

    private fun updateEnemy(deltaTime: Double) {
        enemies.forEach {
            it.update(deltaTime)
        }

        gameStateModel.bullets.forEach {
            it.currentPosition = Circle(
                it.currentPosition.center
                    + it.moveDirection * it.speed * deltaTime,
                it.currentPosition.radius
            )
        }

        gameStateModel.bullets.filter {
            !gameStateModel.stageInfo.bulletExistedRange.collidesWith(it.currentPosition)
        }.forEach {
            gameStateModel.bullets.remove(it)
        }
    }

    private fun updatePlayer(deltaTime: Double, inputDirection: Vector) {
        val player = gameStateModel.player
        val nextPosition = (player.currentPosition.center + inputDirection.normalize() * player.speed * deltaTime)
        player.currentPosition = Circle(nextPosition, player.currentPosition.radius)
            .coerceIn(gameStateModel.stageInfo.gamePlayRange)

        if (inputDirection == Vector.ZERO) return
        when {
            inputDirection.x.absoluteValue >= inputDirection.y.absoluteValue -> {
                when {
                    inputDirection.x > 0 -> player.currentActionType = ActionType.Right
                    else -> player.currentActionType = ActionType.Left
                }
            }

            else -> {
                when {
                    inputDirection.y > 0 -> player.currentActionType = ActionType.Down
                    else -> player.currentActionType = ActionType.Up
                }
            }
        }
    }
}