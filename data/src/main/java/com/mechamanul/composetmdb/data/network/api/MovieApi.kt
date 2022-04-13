package com.mechamanul.composetmdb.data.network.api

import com.mechamanul.composetmdb.data.BuildConfig
import com.mechamanul.composetmdb.data.network.dto.MovieResponse
import com.mechamanul.composetmdb.data.utils.ResponseWrapper
import retrofit2.Response
import retrofit2.http.GET
//import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("/TOP250Movies/{api_key}")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey:String = BuildConfig.IMDBApiKey
    ):Response<MovieResponse>
}