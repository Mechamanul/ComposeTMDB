package com.mechamanul.composetmdb.remote.services


import com.mechamanul.composetmdb.remote.models.MovieResponse
import retrofit2.Response
import retrofit2.http.GET

interface MovieService {
    @GET("TOP250Movies")
    suspend fun getPopularMovies(
    ): Response<MovieResponse>
}