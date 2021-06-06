package com.hamann.inpursuitofdiversion

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.hamann.inpursuitofdiversion.db.GameDB
import com.hamann.inpursuitofdiversion.db.GamesDao
import com.hamann.inpursuitofdiversion.models.Game
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

@RunWith(AndroidJUnit4::class)
class GameDaoTests {
    private lateinit var gameDb: GameDB
    private lateinit var gamesDao: GamesDao
    private val context = InstrumentationRegistry.getInstrumentation().context

    @Before
    fun setup() {
        gameDb = Room
            .inMemoryDatabaseBuilder(context, GameDB::class.java)
            .build()
        gamesDao = gameDb.gamesDao()
    }

    @Test
    fun insertGame_success() {
        val game = Game(
            guid = UUID.randomUUID().toString(),
            id = 101,
            shortDescription = "Test",
            name = "Mario Party",
            releaseDate = Date(System.currentTimeMillis()),
            image = Game.Image(
                thumbUrl = "blah",
                originalUrl = "blah"
            )
        ).also {
            it.associatedSearchString = "Mario"
        }
        runBlocking {
            try {
                gamesDao.insertGames(listOf(game))
            } catch (t: Throwable) {
                assert(false)
            }
        }
        assert(true)
    }
}