package com.hamann.inpursuitofdiversion.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hamann.inpursuitofdiversion.models.GameSearchPagination

@Database(
    entities = [GameSearchPagination::class],
    version = 1,
    exportSchema = false
)
abstract class GameDB: RoomDatabase() {
    abstract fun gameSearchDao(): GameSearchDao
}