package GameEngine.Data

enum class GameState {
    Init,
    Start,
    GamePlay,
    GameOver,
    Restart
}

data class GameStateModel(
    val stageInfo: StageInfo,
    val player: PlayerData,
    val bullets: MutableList<BulletData>,
    var currentState: GameState,
    var score: Double
)