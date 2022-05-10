package com.mechamanul.composetmdb.domain.usecase

import com.mechamanul.composetmdb.domain.models.MovieDetails
import com.mechamanul.composetmdb.domain.repository.MovieRepository
import com.mechamanul.utils.composetmdb.base.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieDetailsUseCaseImpl @Inject constructor(private val movieRepository: MovieRepository) :
    GetMovieDetailsUseCase {
    override suspend fun invoke(id: String): Flow<Result<MovieDetails>> =
        movieRepository.getMovieDetails(id)

}