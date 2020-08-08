package GameEngine.Data

data class GameStateModel(
        val player: PlayerData,
        val enemies: MutableList<EnemyData>
)