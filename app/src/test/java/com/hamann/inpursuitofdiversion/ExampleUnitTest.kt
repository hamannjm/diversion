package com.hamann.inpursuitofdiversion

import com.hamann.inpursuitofdiversion.web.SearchService
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}

@RunWith(JUnit4::class)
class QuickTest{
    @Test
    fun quickWebTest() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.giantbomb.com/api/")
            .client(
                OkHttpClient.Builder()
                    .callTimeout(30, TimeUnit.SECONDS)
                    .build()
            )
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()
        val service = retrofit.create(SearchService::class.java)

        runBlocking {
            val resultsWrapper = service.searchGames("name:skyrim")
            assertTrue(resultsWrapper.results.any())
        }
    }
}