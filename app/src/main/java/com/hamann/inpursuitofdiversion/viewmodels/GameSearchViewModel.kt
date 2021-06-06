package com.hamann.inpursuitofdiversion.viewmodels

import androidx.lifecycle.*
import com.hamann.inpursuitofdiversion.models.Game
import com.hamann.inpursuitofdiversion.models.Resource
import com.hamann.inpursuitofdiversion.repository.GamesRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class GameSearchViewModel @Inject constructor(
    private val repo: GamesRepository
): ViewModel() {
    private val gamesMediator: MediatorLiveData<Resource<List<Game>>> = MediatorLiveData()
    private val _games: MutableLiveData<Resource<List<Game>>> = MutableLiveData()
    val games: LiveData<Resource<List<Game>>> = gamesMediator

    init {
        gamesMediator.addSource(_games) {
            gamesMediator.postValue(it)
        }
        gamesMediator.addSource(repo.games) {
            gamesMediator.postValue(it)
        }
    }

    suspend fun fetchGames(searchString: String) {
        if (searchString.isBlank()) {
            _games.value = Resource.success(emptyList())
            return
        }

        viewModelScope.launch {
            repo.fetchGames("name:$searchString")
        }
    }
}