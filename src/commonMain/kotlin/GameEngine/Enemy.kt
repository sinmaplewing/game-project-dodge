package GameEngine

import GameEngine.Basic.Circle
import GameEngine.Data.BulletData
import GameEngine.Data.EnemyInfo
import GameEngine.Data.GameStateModel
import kotlin.random.Random

const val BULLET_SIZE = 5.0
const val BULLET_MIN_SPEED = 250.0
const val BULLET_MAX_SPEED = 250.0

class Enemy(
    val gameStateModel: GameStateModel,
    val enemyInfo: EnemyInfo
) {
    private var lastGeneratedDuration = 0.0

    fun update(deltaTime: Double) {
        enemyInfo.run {
            lastGeneratedDuration += deltaTime
            if (lastGeneratedDuration >= duration) {
                lastGeneratedDuration -= duration

                if (Random.nextDouble(0.0, 1.0) <= probability) {
                    gameStateModel.bullets.add(
                        BulletData(
                            Circle(position, BULLET_SIZE),
                            Random.nextDouble(BULLET_MIN_SPEED, BULLET_MAX_SPEED),
                            (gameStateModel.player.currentPosition.center - position)
                                .normalize()
                        )
                    )
                }
            }
        }
    }

}