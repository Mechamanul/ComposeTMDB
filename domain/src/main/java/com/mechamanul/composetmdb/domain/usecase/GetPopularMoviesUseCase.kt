package com.mechamanul.composetmdb.domain.usecase

import com.mechamanul.composetmdb.domain.models.Movie
import com.mechamanul.utils.composetmdb.base.Result
import kotlinx.coroutines.flow.Flow

interface GetPopularMoviesUseCase {
    suspend operator fun invoke(): Flow<Result<List<Movie>>>
}