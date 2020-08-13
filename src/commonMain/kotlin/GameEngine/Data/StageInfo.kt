package GameEngine.Data

import GameEngine.Basic.Rectangle

data class StageInfo(
    val gamePlayRange: Rectangle,
    val enemyGeneratorsData: Array<EnemyInfo>,
    val enemyExistedRange: Rectangle
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as StageInfo

        if (gamePlayRange != other.gamePlayRange) return false
        if (!enemyGeneratorsData.contentEquals(other.enemyGeneratorsData)) return false
        if (enemyExistedRange != other.enemyExistedRange) return false

        return true
    }

    override fun hashCode(): Int {
        var result = gamePlayRange.hashCode()
        result = 31 * result + enemyGeneratorsData.contentHashCode()
        result = 31 * result + enemyExistedRange.hashCode()
        return result
    }
}