package GameEngine.Data

data class GameStateModel(
    val stageInfo: StageInfo,
    val player: PlayerData,
    val enemies: MutableList<EnemyData>
)