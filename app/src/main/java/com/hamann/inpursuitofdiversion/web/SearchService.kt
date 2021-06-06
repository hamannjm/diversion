package com.hamann.inpursuitofdiversion.web

import com.hamann.inpursuitofdiversion.models.GameSearchResultsWrapper
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {

    @GET("games/")
    suspend fun searchGames(
        @Query("filter", encoded = true)
        searchString: String,
        @Query("api_key")
        apiKey: String = "9d45436f87d3848d2bdcce810bacb6df57dfd134",
        @Query("format")
        format: String = "json",
        @Query("limit")
        pageSize: Int = 100,
        @Query("offset")
        offset: Int = 0): GameSearchResultsWrapper
}