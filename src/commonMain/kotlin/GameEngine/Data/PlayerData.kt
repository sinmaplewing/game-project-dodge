package GameEngine.Data

import GameEngine.Basic.Circle

data class PlayerData(
    var currentPosition: Circle,
    val speed: Double,
    var isDead: Boolean,
    var currentActionType: ActionType
)