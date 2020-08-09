package GameEngine.Data

data class GameStateModel(
        val gamePlayContext: GamePlayContext,
        val player: PlayerData,
        val enemies: MutableList<EnemyData>
)