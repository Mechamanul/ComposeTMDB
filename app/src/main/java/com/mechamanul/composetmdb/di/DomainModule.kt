package com.mechamanul.composetmdb.di

import com.mechamanul.composetmdb.domain.repository.MovieRepository
import com.mechamanul.composetmdb.domain.usecase.GetPopularMoviesUseCase
import com.mechamanul.composetmdb.domain.usecase.GetPopularMoviesUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {
    @Binds
    abstract fun bindGetPopularMovies(getPopularMoviesUseCaseImpl: GetPopularMoviesUseCaseImpl): GetPopularMoviesUseCase
//    fun provideGetPopularMovies(repository: MovieRepository) = GetPopularMoviesUseCaseImpl(repository)
}