package com.mechamanul.composetmdb.domain.usecase

import com.mechamanul.composetmdb.domain.models.Movie
import com.mechamanul.composetmdb.domain.repository.MovieRepository
import com.mechamanul.utils.composetmdb.base.Result
import kotlinx.coroutines.flow.Flow

class GetPopularMoviesUseCaseImpl(private val movieRepository: MovieRepository) :
    GetPopularMoviesUseCase {
    override suspend fun invoke(): Flow<Result<List<Movie>>> = movieRepository.getPopularMovies()

}