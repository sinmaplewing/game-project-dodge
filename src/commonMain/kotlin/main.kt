import com.soywiz.korge.*
import com.soywiz.korge.scene.Module
import com.soywiz.korim.color.RGBA
import com.soywiz.korinject.AsyncInjector
import com.soywiz.korma.geom.SizeInt

suspend fun main() = Korge(Korge.Config(
	module = GameModule))

object GameModule: Module() {
	override val mainScene = GamePlayScene::class
	override val size = SizeInt(640, 480)
	override val title = "Dodge"

	override suspend fun AsyncInjector.configure() {
		mapPrototype { GamePlayScene() }
	}
}