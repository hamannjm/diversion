package com.hamann.inpursuitofdiversion.viewmodels

import androidx.lifecycle.ViewModel
import com.hamann.inpursuitofdiversion.db.GamesDao
import com.hamann.inpursuitofdiversion.models.Game
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GameDetailViewModel @Inject constructor(
    private val gamesDao: GamesDao
): ViewModel() {

    suspend fun findGame(gameGuid: String): Game = withContext(Dispatchers.Default) {
        gamesDao.getGame(gameGuid)
    }
}