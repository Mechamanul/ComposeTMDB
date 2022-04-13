package com.mechamanul.composetmdb.di

import com.mechamanul.composetmdb.data.repository.MovieRepositoryImpl
import com.mechamanul.composetmdb.data.repository.di.RepositoryModule
import com.mechamanul.composetmdb.domain.repository.MovieRepository
import com.mechamanul.composetmdb.domain.usecase.GetPopularMoviesUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [RepositoryModule::class])
@InstallIn(SingletonComponent::class)
object DomainModule {
    @Provides
    fun provideGetPopularMovies(repository: MovieRepository) = GetPopularMoviesUseCaseImpl(repository)
}