import GameEngine.Basic.Point
import GameEngine.Basic.Rectangle
import GameEngine.Data.*
import GameEngine.DodgeGameEngine
import Input.PlayerMoveInputHandler
import Input.PlayerMoveKeyboardInputHandler
import Input.PlayerMoveMouseInputHandler
import Input.PlayerMoveTouchInputHandler
import com.soywiz.korge.*
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
		PlayerData(GameEngine.Basic.Circle(Point(0.0, 0.0), 10.0), 500.0),
		mutableListOf()
	)

	val engine = DodgeGameEngine(model)
	val inputHandler = PlayerMoveInputHandler(
		arrayOf(
			PlayerMoveMouseInputHandler(views.input),
			PlayerMoveTouchInputHandler(views.input),
			PlayerMoveKeyboardInputHandler(views.input)
		)
	)
	val playerView = circle(model.player.currentPosition.radius, Colors.RED)
	val enemyViews = mutableListOf<Circle>()

	addUpdater {
		inputHandler.update()
		engine.update(it.seconds, inputHandler.currentInputDirection)

		updateEnemyViews(
			this,
			model.enemies.toTypedArray(),
			enemyViews
		)

		playerView.xy(
			model.player.currentPosition.center.x - model.player.currentPosition.radius,
			model.player.currentPosition.center.y - model.player.currentPosition.radius)
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
		println(currentEnemy.currentPosition)

		circle.color = Colors.ORANGE
		circle.radius = currentEnemy.currentPosition.radius
		circle.xy(
			currentEnemy.currentPosition.center.x,
			currentEnemy.currentPosition.center.y
		)
	}
}