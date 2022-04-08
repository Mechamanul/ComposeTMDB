package com.mechamanul.composetmdb.domain.repository

import com.mechamanul.composetmdb.domain.models.Movie
import com.mechamanul.utils.composetmdb.base.Result
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getPopularMovies(): Flow<Result<List<Movie>>>
}