package GameEngine.Data

import GameEngine.Basic.Rectangle

data class StageInfo(
    val gamePlayRange: Rectangle,
    val enemiesData: Array<EnemyInfo>,
    val bulletExistedRange: Rectangle
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as StageInfo

        if (gamePlayRange != other.gamePlayRange) return false
        if (!enemiesData.contentEquals(other.enemiesData)) return false
        if (bulletExistedRange != other.bulletExistedRange) return false

        return true
    }

    override fun hashCode(): Int {
        var result = gamePlayRange.hashCode()
        result = 31 * result + enemiesData.contentHashCode()
        result = 31 * result + bulletExistedRange.hashCode()
        return result
    }
}