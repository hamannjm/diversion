package com.hamann.inpursuitofdiversion.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.hamann.inpursuitofdiversion.db.GameDB
import com.hamann.inpursuitofdiversion.db.GameSearchDao
import com.hamann.inpursuitofdiversion.db.GamesDao
import com.hamann.inpursuitofdiversion.models.Game
import com.hamann.inpursuitofdiversion.models.Resource
import com.hamann.inpursuitofdiversion.web.SearchService
import kotlinx.coroutines.*
import javax.inject.Inject

class GamesRepository @Inject constructor(
    private val gamesDao: GamesDao,
    private val gameSearchDao: GameSearchDao,
    private val searchService: SearchService
) {
    private val gameResultsMediator: MediatorLiveData<Resource<List<Game>>> = MediatorLiveData()
    val games: LiveData<Resource<List<Game>>> = gameResultsMediator

    suspend fun fetchGames(searchString: String) {
        gameResultsMediator.postValue(Resource.loading())

        val webResults = try {
            searchService.searchGames(searchString)
        } catch (t: Throwable) {
            gameResultsMediator.postValue(Resource.error(t.localizedMessage ?: "Something went wrong fetching new games!"))
            return
        }
        if (webResults.statusCode != 1) {
            gameResultsMediator.postValue(Resource.error(webResults.error))
            return
        }
        //TODO log paging information and figure out how to get next page here?

        try {
            withContext(Dispatchers.Default) {
                gamesDao.insertGames(
                    webResults.results.map { game ->
                        game.associatedSearchString = searchString
                        game
                    }.toList()
                )
            }
        } catch (t: Throwable) {
            gameResultsMediator.postValue(Resource.error(t.localizedMessage ?: "Error saving results!"))
        }

        gameResultsMediator.addSource(gamesDao.getGamesFromSearchString(searchString)) {
            gameResultsMediator.postValue(Resource.success(it))
        }
    }
}