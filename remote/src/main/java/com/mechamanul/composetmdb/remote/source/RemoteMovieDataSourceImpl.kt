package com.mechamanul.composetmdb.remote.source

import com.mechamanul.composetmdb.data.source.RemoteMovieDataSource
import com.mechamanul.composetmdb.domain.models.Movie
import com.mechamanul.composetmdb.remote.models.MovieResponseDetail
import com.mechamanul.composetmdb.remote.services.MovieService
import com.mechamanul.utils.composetmdb.base.Result
import javax.inject.Inject

//import com.mechamanul.utils.composetmdb.base.mapper.Mapper

class RemoteMovieDataSourceImpl @Inject constructor(
    private val movieService: MovieService
) : RemoteMovieDataSource {
    override suspend fun getPopularMovies(): Result<List<Movie>> {
        val response = movieService.getPopularMovies()
        if (response.isSuccessful) {
            val result =
                response.body()
            result?.items?.let {
                return Result.Success(it.map { fetchedMovieDetails -> fetchedMovieDetails.mapToDomain() })
            } ?: Result.Error(throwable = Throwable(result?.errorMessage))
        }
        return Result.Error(Throwable("Error with internet connection"))
    }

    private fun MovieResponseDetail.mapToDomain(): Movie {
        return Movie(
            name = this.title,
            year = this.year.toInt(),
            poster = this.image,
            crew = this.crew
        )
    }
}