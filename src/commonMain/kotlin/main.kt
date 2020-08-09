import GameEngine.Basic.Point
import GameEngine.Basic.Rectangle
import GameEngine.Data.*
import GameEngine.DodgeGameEngine
import Input.PlayerMoveInputHandler
import Input.PlayerMoveKeyboardInputHandler
import Input.PlayerMoveMouseInputHandler
import Input.PlayerMoveTouchInputHandler
import com.soywiz.kmem.toIntFloor
import com.soywiz.korev.Key
import com.soywiz.korge.*
import com.soywiz.korge.input.Input
import com.soywiz.korge.view.*
import com.soywiz.korim.color.Colors

suspend fun main() = Korge(
	width = 640,
	height = 480,
	virtualWidth = 640,
	virtualHeight = 480,
	bgcolor = Colors["#2b2b2b"]
) {
	val model = GameStateModel(
		StageInfo(
			Rectangle(
				Point(0.0, 0.0),
				Point(width, height)
			),
			arrayOf(
				EnemyGeneratorInfo(
					Point(-50.0, -50.0),
					1.0,
					0.5
				),
				EnemyGeneratorInfo(
					Point(width + 50.0, -50.0),
					0.5,
					0.25
				),
				EnemyGeneratorInfo(
					Point(-50.0, height + 50.0),
					0.3,
					0.1
				),
				EnemyGeneratorInfo(
					Point(width + 50.0, height + 50.0),
					1.5,
					0.9
				)
			),
			Rectangle(
				Point(-100.0, -100.0),
				Point(width + 100.0, height + 100.0)
			)
		),
		PlayerData(
			GameEngine.Basic.Circle(
				Point(width / 2.0, height / 2.0), 10.0
			),
			500.0,
			false
		),
		mutableListOf(),
		GameState.Init,
		0.0
	)

	val engine = DodgeGameEngine(model)
	val inputHandler = PlayerMoveInputHandler(
		arrayOf(
			PlayerMoveMouseInputHandler(views.input),
			PlayerMoveTouchInputHandler(views.input),
			PlayerMoveKeyboardInputHandler(views.input)
		)
	)
	val playerView = Circle(model.player.currentPosition.radius, Colors.RED)
		.xy(
			model.player.currentPosition.center.x - model.player.currentPosition.radius,
			model.player.currentPosition.center.y - model.player.currentPosition.radius
		)
	val enemyViews = mutableListOf<Circle>()
	val scoreText = text("").xy(0, 0)
	val pressToStartText = text("Press SPACE / SCREEN to start")
	pressToStartText.xy(
		width / 2 - pressToStartText.width / 2,
		height / 2 - pressToStartText.height / 2
	)

	addUpdater {
		when (model.currentState) {
			GameState.Init -> {
				if (isPressed(views.input)) {
					model.currentState = GameState.GamePlay
					removeChild(pressToStartText)

					addChild(playerView)
					addChild(scoreText)
				}
			}

			GameState.GamePlay -> {
				inputHandler.update()
				engine.update(it.seconds, inputHandler.currentInputDirection)

				model.score += it.seconds
				scoreText.text = "Score: ${model.score.toIntFloor()}"

				updateEnemyViews(
					this,
					model.enemies.toTypedArray(),
					enemyViews
				)

				playerView.xy(
					model.player.currentPosition.center.x - model.player.currentPosition.radius,
					model.player.currentPosition.center.y - model.player.currentPosition.radius
				)

				if (model.player.isDead) {
					model.currentState = GameState.GameOver
				}
			}

			else -> {}
		}
	}
}

private fun updateEnemyViews(
	stage: Stage,
	enemiesData: Array<EnemyData>,
	enemyViews: MutableList<Circle>
) {
	while (enemyViews.size < enemiesData.size) {
		enemyViews.add(stage.circle())
	}
	while (enemyViews.size > enemiesData.size) {
		stage.removeChild(enemyViews.last())
		enemyViews.removeAt(enemyViews.lastIndex)
	}

	enemyViews.forEachIndexed { index, circle ->
		val currentEnemy = enemiesData[index]

		circle.color = Colors.ORANGE
		circle.radius = currentEnemy.currentPosition.radius
		circle.xy(
			currentEnemy.currentPosition.center.x - currentEnemy.currentPosition.radius,
			currentEnemy.currentPosition.center.y - currentEnemy.currentPosition.radius
		)
	}
}

fun isPressed(input: Input) =
	input.keys.justReleased(Key.SPACE) ||
		input.mouseButtons > 0 ||
		input.activeTouches.isNotEmpty()