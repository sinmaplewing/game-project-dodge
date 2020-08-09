package GameEngine.Data

enum class GameState {
    Init,
    GamePlay,
    GameOver
}

data class GameStateModel(
    val stageInfo: StageInfo,
    val player: PlayerData,
    val enemies: MutableList<EnemyData>,
    var currentState: GameState,
    var score: Double
)