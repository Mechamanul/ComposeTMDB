package com.mechamanul.composetmdb.di

import com.mechamanul.composetmdb.data.repository.MovieRepositoryImpl
import com.mechamanul.composetmdb.domain.repository.MovieRepository
import com.mechamanul.composetmdb.remote.services.MovieService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    abstract fun bindMovieRepository(movieRepositoryImpl: MovieRepositoryImpl): MovieRepository
}