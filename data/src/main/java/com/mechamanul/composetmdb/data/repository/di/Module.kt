package com.mechamanul.composetmdb.data.repository.di

import com.mechamanul.composetmdb.data.BuildConfig
import com.mechamanul.composetmdb.data.network.api.MovieApi
import com.mechamanul.composetmdb.data.network.di.NetworkModule
import com.mechamanul.composetmdb.data.repository.MovieRepositoryImpl
import com.mechamanul.composetmdb.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
object Module {
    @Provides
    @Singleton
    fun provideMovieApi(retrofit: Retrofit):MovieApi{
        return retrofit.create(MovieApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(movieApi: MovieApi): MovieRepository {
        return MovieRepositoryImpl(movieApi)
    }
}