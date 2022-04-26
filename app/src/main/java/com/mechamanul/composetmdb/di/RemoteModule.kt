package com.mechamanul.composetmdb.di

import com.mechamanul.composetmdb.BuildConfig
import com.mechamanul.composetmdb.data.source.RemoteMovieDataSource
import com.mechamanul.composetmdb.remote.services.MovieService
import com.mechamanul.composetmdb.remote.source.RemoteMovieDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Provides
    @Singleton
    fun provideNetworkInterceptor(): Interceptor = Interceptor { chain ->
        val request = chain.request()
        val originalHttpUrl = request.url()
        val url =
            originalHttpUrl.newBuilder().addQueryParameter("api_key", BuildConfig.API_KEY).build()
        val builder = request.newBuilder()
            .addHeader("Accept-Language", "en-US")
            .addHeader("Content-Type", "application/json")
            .url(url)
            .build()
        return@Interceptor chain.proceed(builder)
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().apply {

            addConverterFactory(GsonConverterFactory.create())
            client(okHttpClient)
            baseUrl(BuildConfig.BASE_API_URL)

        }.build()
    }

    @Singleton
    @Provides
    fun provideOkHttp(interceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder().apply {
            connectTimeout(60, TimeUnit.SECONDS)
            readTimeout(60, TimeUnit.SECONDS)
            writeTimeout(60, TimeUnit.SECONDS)
            addInterceptor(interceptor)
        }.build()
    }

    @Singleton
    @Provides
    fun provideMovieService(retrofit: Retrofit):MovieService = retrofit.create(MovieService::class.java)

}

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteBinds {
    @Binds
    abstract fun bindsRemoteDataSource(remoteMovieDataSourceImpl: RemoteMovieDataSourceImpl): RemoteMovieDataSource
}