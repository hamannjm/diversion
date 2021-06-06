package com.hamann.inpursuitofdiversion.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hamann.inpursuitofdiversion.models.Game

@Dao
interface GamesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGames(games: List<Game>)

    @Query("SELECT * FROM games WHERE associatedSearchString = :searchString")
    fun getGamesFromSearchString(searchString: String): LiveData<List<Game>>

    @Query("SELECT * FROM games WHERE guid = :gameGuid")
    suspend fun getGame(gameGuid: String): Game
}