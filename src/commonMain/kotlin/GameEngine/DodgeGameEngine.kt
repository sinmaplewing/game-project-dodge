package GameEngine

import GameEngine.Basic.Circle
import GameEngine.Basic.Vector
import GameEngine.Data.GameStateModel

class DodgeGameEngine(
    private val gameStateModel: GameStateModel
) {
    private val enemyGenerators: Array<EnemyGenerator> =
        gameStateModel.stageInfo.enemyGeneratorsData
            .map {
                EnemyGenerator(gameStateModel, it)
            }.toTypedArray()


    fun update(deltaTime: Double, inputDirection: Vector) {
        updateEnemy(deltaTime)
        updatePlayer(deltaTime, inputDirection)

        if (gameStateModel.enemies.any {
                it.currentPosition.collidesWith(
                    gameStateModel.player.currentPosition
                )
        }) {
            gameStateModel.player.isDead = true
        }
    }

    private fun updateEnemy(deltaTime: Double) {
        enemyGenerators.forEach {
            it.update(deltaTime)
        }

        gameStateModel.enemies.forEach {
            it.currentPosition = Circle(
                it.currentPosition.center
                    + it.moveDirection * it.speed * deltaTime,
                it.currentPosition.radius
            )
        }

        gameStateModel.enemies.filter {
            !gameStateModel.stageInfo.enemyExistedRange.collidesWith(it.currentPosition)
        }.forEach {
            gameStateModel.enemies.remove(it)
        }
    }

    private fun updatePlayer(deltaTime: Double, inputDirection: Vector) {
        val player = gameStateModel.player
        val nextPosition = (player.currentPosition.center + inputDirection.normalize() * player.speed * deltaTime)
        player.currentPosition = Circle(nextPosition, player.currentPosition.radius)
            .coerceIn(gameStateModel.stageInfo.gamePlayRange)
    }
}