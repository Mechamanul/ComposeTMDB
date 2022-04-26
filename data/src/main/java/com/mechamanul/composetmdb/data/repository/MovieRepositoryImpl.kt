package com.mechamanul.composetmdb.data.repository


import com.mechamanul.composetmdb.data.source.RemoteMovieDataSource
import com.mechamanul.composetmdb.domain.models.Movie
import com.mechamanul.composetmdb.domain.repository.MovieRepository
import com.mechamanul.utils.composetmdb.base.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val remoteDataSource: RemoteMovieDataSource) :
    MovieRepository {
    override suspend fun getPopularMovies(): Flow<Result<List<Movie>>> {
        return flow {
            emit(Result.Loading)
            val apiResponse = remoteDataSource.getPopularMovies()
            emit(apiResponse)
            // later will be caching logic and pagination maybe
        }
    }


}