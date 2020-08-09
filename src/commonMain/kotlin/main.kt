import GameEngine.Basic.Point
import GameEngine.Basic.Rectangle
import GameEngine.Data.EnemyData
import GameEngine.Data.GamePlayContext
import GameEngine.Data.GameStateModel
import GameEngine.Data.PlayerData
import GameEngine.DodgeGameEngine
import Input.PlayerMoveInputHandler
import Input.PlayerMoveKeyboardInputHandler
import Input.PlayerMoveMouseInputHandler
import Input.PlayerMoveTouchInputHandler
import com.soywiz.korge.*
import com.soywiz.korge.view.*
import com.soywiz.korim.color.Colors

suspend fun main() = Korge(
	width = 1920,
	height = 1080,
	virtualWidth = 1920,
	virtualHeight = 1080,
	bgcolor = Colors["#2b2b2b"]
) {
	val model = GameStateModel(
		GamePlayContext(Rectangle(
			Point(0.0, 0.0),
			Point(width, height)
		)),
		PlayerData(GameEngine.Basic.Circle(Point(0.0, 0.0), 20.0), 500.0),
		mutableListOf(
			EnemyData(
				Point(100.0, 100.0), 10.0, 5.0
			),
			EnemyData(
				Point(150.0, 150.0), 10.0, 5.0
			)
		)
	)

	val engine = DodgeGameEngine(model)
	val inputHandler = PlayerMoveInputHandler(
		arrayOf(
			PlayerMoveMouseInputHandler(views.input),
			PlayerMoveTouchInputHandler(views.input),
			PlayerMoveKeyboardInputHandler(views.input)
		)
	)
	val playerView = circle(model.player.currentPosition.radius, Colors.BLUE)
	val enemiesView = model.enemies.map { circle(it.size, Colors.RED) }

	addUpdater {
		inputHandler.update()
		engine.update(it.seconds, inputHandler.currentInputDirection)
		playerView.xy(
			model.player.currentPosition.center.x - model.player.currentPosition.radius,
			model.player.currentPosition.center.y - model.player.currentPosition.radius)
		enemiesView.forEachIndexed { index, circle ->
			val enemy = model.enemies[index]
			circle.xy(enemy.currentPosition.x, enemy.currentPosition.y)
		}
	}
}