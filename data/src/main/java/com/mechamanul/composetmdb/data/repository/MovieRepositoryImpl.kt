package com.mechamanul.composetmdb.data.repository

import com.mechamanul.composetmdb.data.network.api.MovieApi
import com.mechamanul.composetmdb.data.network.dto.MovieResponseDetail
import com.mechamanul.composetmdb.domain.models.Movie
import com.mechamanul.composetmdb.domain.repository.MovieRepository
import com.mechamanul.utils.composetmdb.base.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepositoryImpl(private val moviesApi: MovieApi) : MovieRepository {
    override suspend fun getPopularMovies(): Flow<Result<List<Movie>>> {
        return flow {
            emit(Result.Loading)
            val response = moviesApi.getPopularMovies()
            if (response.isSuccessful) {
                val body = response.body()
                if (body?.errorMessage?.isEmpty() == true) {
                    emit(Result.Error(Exception(body.errorMessage)))
                } else {
                    val listOfMovies = mutableListOf<Movie>()
                    body?.items?.forEach { movieResponseDetail ->
                        listOfMovies.add(
                            movieResponseDetail.mapToDomain()
                        )
                    }
                    emit(Result.Success(listOfMovies))
                }
            } else {
                emit(Result.Error(Exception("NetworkError")))
            }
        }
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