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
    val enemies: MutableList<EnemyData>,
    var currentState: GameState,
    var score: Double
)