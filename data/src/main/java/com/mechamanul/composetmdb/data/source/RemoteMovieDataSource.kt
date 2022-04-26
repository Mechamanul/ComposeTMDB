package com.mechamanul.composetmdb.data.source

import com.mechamanul.composetmdb.domain.models.Movie
import com.mechamanul.utils.composetmdb.base.Result

interface RemoteMovieDataSource {
    suspend fun getPopularMovies(): Result<List<Movie>>
}