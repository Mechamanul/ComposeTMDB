package com.mechamanul.composetmdb.remote.services


import com.mechamanul.composetmdb.remote.models.MovieDetailsRemote
import com.mechamanul.composetmdb.remote.models.PopularMovies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieService {
    @GET("TOP250Movies")
    suspend fun getPopularMovies(
    ): Response<PopularMovies>

    @GET("Title/{id}")
    suspend fun getMovieDetails(
        @Path(
            "id",
            encoded = true
        ) id: String
    ): Response<MovieDetailsRemote>

}