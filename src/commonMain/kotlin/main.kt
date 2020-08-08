import GameEngine.Basic.Point
import GameEngine.Data.EnemyData
import GameEngine.Data.GameStateModel
import GameEngine.Data.PlayerData
import GameEngine.DodgeGameEngine
import com.soywiz.klock.seconds
import com.soywiz.korev.Key
import com.soywiz.korge.*
import com.soywiz.korge.input.Input
import com.soywiz.korge.tween.*
import com.soywiz.korge.view.*
import com.soywiz.korim.color.Colors
import com.soywiz.korim.format.*
import com.soywiz.korio.file.std.*
import com.soywiz.korma.geom.degrees
import com.soywiz.korma.interpolation.Easing

suspend fun main() = Korge(width = 512, height = 512, bgcolor = Colors["#2b2b2b"]) {
	val model = GameStateModel(
		PlayerData(Point(0.0, 0.0), 20.0, 5.0),
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
	val inputHandler = InputHandler(views.input)
	val playerView = circle(model.player.size, Colors.BLUE)
	val enemiesView = model.enemies.map { circle(it.size, Colors.RED) }

	addUpdater {
		engine.update(inputHandler.getCurrentInputDirection())
		playerView.xy(model.player.currentPosition.x, model.player.currentPosition.y)
		enemiesView.forEachIndexed { index, circle ->
			val enemy = model.enemies[index]
			circle.xy(enemy.currentPosition.x, enemy.currentPosition.y)
		}
	}
}