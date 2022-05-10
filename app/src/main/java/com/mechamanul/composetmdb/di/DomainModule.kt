package com.mechamanul.composetmdb.di

import com.mechamanul.composetmdb.domain.usecase.GetMovieDetailsUseCase
import com.mechamanul.composetmdb.domain.usecase.GetMovieDetailsUseCaseImpl
import com.mechamanul.composetmdb.domain.usecase.GetPopularMoviesUseCase
import com.mechamanul.composetmdb.domain.usecase.GetPopularMoviesUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {
    @Binds
    abstract fun bindGetPopularMovies(getPopularMoviesUseCaseImpl: GetPopularMoviesUseCaseImpl): GetPopularMoviesUseCase

    @Binds
    abstract fun bindGetMovieDetails(getMovieDetailsUseCaseImpl: GetMovieDetailsUseCaseImpl): GetMovieDetailsUseCase
}