package SpriteAnimation

import GameEngine.Data.ActionType
import com.soywiz.korge.view.SpriteAnimation
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.resourcesVfs

class PersonSpriteAnimationLoader {
    companion object{
        suspend fun load(fileName: String): Map<ActionType, SpriteAnimation> {
            val spriteMap = resourcesVfs[fileName].readBitmap()
            val columnSize = spriteMap.width / 3
            val rowSize = spriteMap.height / 4

            return mapOf(
                ActionType.Up to
                    SpriteAnimation(
                        spriteMap = spriteMap,
                        spriteWidth = columnSize,
                        spriteHeight = rowSize,
                        marginTop = 0,
                        marginLeft = 0,
                        columns = 3,
                        rows = 1
                    ),
                ActionType.Right to
                    SpriteAnimation(
                        spriteMap = spriteMap,
                        spriteWidth = columnSize,
                        spriteHeight = rowSize,
                        marginTop = rowSize,
                        marginLeft = 0,
                        columns = 3,
                        rows = 1
                    ),
                ActionType.Down to
                    SpriteAnimation(
                        spriteMap = spriteMap,
                        spriteWidth = columnSize,
                        spriteHeight = rowSize,
                        marginTop = rowSize * 2,
                        marginLeft = 0,
                        columns = 3,
                        rows = 1
                    ),
                ActionType.Left to
                    SpriteAnimation(
                        spriteMap = spriteMap,
                        spriteWidth = columnSize,
                        spriteHeight = rowSize,
                        marginTop = rowSize * 3,
                        marginLeft = 0,
                        columns = 3,
                        rows = 1
                    )
            )
        }
    }
}