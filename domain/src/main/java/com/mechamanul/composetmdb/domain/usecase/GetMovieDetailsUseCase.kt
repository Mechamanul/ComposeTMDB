package com.mechamanul.composetmdb.domain.usecase

import com.mechamanul.composetmdb.domain.models.MovieDetails
import com.mechamanul.utils.composetmdb.base.Result
import kotlinx.coroutines.flow.Flow

interface GetMovieDetailsUseCase {
    suspend operator fun invoke(id: String): Flow<Result<MovieDetails>>
}