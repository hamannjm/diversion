package com.hamann.inpursuitofdiversion.di

import android.content.Context
import androidx.room.Room
import com.hamann.inpursuitofdiversion.db.GameDB
import com.hamann.inpursuitofdiversion.db.GameSearchDao
import com.hamann.inpursuitofdiversion.db.GamesDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PersistenceModule {

    @Singleton
    @Provides
    fun providesGameDB(appContext: Context): GameDB {
        return Room
            .databaseBuilder(appContext, GameDB::class.java, "game.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun providesGameSearchDao(db: GameDB): GameSearchDao = db.gameSearchDao()

    @Singleton
    @Provides
    fun providesGamesDao(db: GameDB): GamesDao = db.gamesDao()
}