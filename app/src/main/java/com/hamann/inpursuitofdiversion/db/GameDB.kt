package com.hamann.inpursuitofdiversion.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hamann.inpursuitofdiversion.models.Game
import com.hamann.inpursuitofdiversion.models.GameSearchPagination

@Database(
    entities = [GameSearchPagination::class, Game::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class GameDB: RoomDatabase() {
    abstract fun gameSearchDao(): GameSearchDao
    abstract fun gamesDao(): GamesDao
}