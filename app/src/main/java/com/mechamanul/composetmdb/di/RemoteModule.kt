package com.mechamanul.composetmdb.di

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.mechamanul.composetmdb.BuildConfig
import com.mechamanul.composetmdb.data.source.RemoteMovieDataSource
import com.mechamanul.composetmdb.remote.services.MovieService
import com.mechamanul.composetmdb.remote.source.RemoteMovieDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LoggingInterceptor

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ApiInterceptor


@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Provides
    @ApiInterceptor
    @Singleton
    fun provideNetworkInterceptor(): Interceptor = Interceptor { chain ->
        val request = chain.request()
        val originalHttpUrl = request.url
        Log.d("EncodedSegments:", originalHttpUrl.encodedPath)
        Log.d("PathSegments:", "${originalHttpUrl.pathSegments}")
        val segments = originalHttpUrl.pathSegments
        val newSegments: MutableList<String> = segments.toMutableList()
        newSegments.add(3, BuildConfig.API_KEY)

        val url =
            HttpUrl.Builder().scheme("https").host("imdb-api.com")
                .addPathSegments(newSegments.joinToString("/")).build()

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
            baseUrl("https://imdb-api.com/en/API/")

        }.build()
    }

    @Singleton
    @Provides
    fun provideOkHttp(
        @ApiInterceptor interceptor: Interceptor,
        @LoggingInterceptor loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder().apply {
            connectTimeout(60, TimeUnit.SECONDS)
            readTimeout(60, TimeUnit.SECONDS)
            writeTimeout(60, TimeUnit.SECONDS)
            addInterceptor(interceptor).addInterceptor(loggingInterceptor)
        }.build()
    }

    @LoggingInterceptor
    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    @Singleton
    @Provides
    fun provideMovieService(retrofit: Retrofit): MovieService =
        retrofit.create(MovieService::class.java)

    @Singleton
    @Provides
    fun provideGlide(@ApplicationContext context: Context): RequestBuilder<Drawable> {
        return Glide.with(context).asDrawable().thumbnail(0.1f)
            .transition(DrawableTransitionOptions.withCrossFade())
    }

}

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteBinds {
    @Binds
    abstract fun bindsRemoteDataSource(remoteMovieDataSourceImpl: RemoteMovieDataSourceImpl): RemoteMovieDataSource
}