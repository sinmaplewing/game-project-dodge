package GameEngine

import GameEngine.Basic.Circle
import GameEngine.Basic.Point
import GameEngine.Data.EnemyData
import GameEngine.Data.EnemyGeneratorInfo
import GameEngine.Data.GameStateModel
import kotlin.random.Random

const val ENEMY_SIZE = 5.0
const val ENEMY_MIN_SPEED = 250.0
const val ENEMY_MAX_SPEED = 1000.0

class EnemyGenerator(
    val gameStateModel: GameStateModel,
    val enemyGeneratorInfo: EnemyGeneratorInfo
) {
    private var lastGeneratedDuration = 0.0

    fun update(deltaTime: Double) {
        enemyGeneratorInfo.run {
            lastGeneratedDuration += deltaTime
            if (lastGeneratedDuration >= duration) {
                lastGeneratedDuration -= duration

                if (Random.nextDouble(0.0, 1.0) <= probability) {
                    gameStateModel.enemies.add(
                        EnemyData(
                            Circle(position, ENEMY_SIZE),
                            Random.nextDouble(ENEMY_MIN_SPEED, ENEMY_MAX_SPEED),
                            (gameStateModel.player.currentPosition.center - position)
                                .normalize()
                        )
                    )
                }
            }
        }
    }

}