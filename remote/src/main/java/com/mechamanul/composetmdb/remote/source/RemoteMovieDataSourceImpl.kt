package com.mechamanul.composetmdb.remote.source

import com.mechamanul.composetmdb.data.source.RemoteMovieDataSource
import com.mechamanul.composetmdb.domain.models.Actor
import com.mechamanul.composetmdb.domain.models.Movie
import com.mechamanul.composetmdb.domain.models.MovieDetails
import com.mechamanul.composetmdb.remote.models.ActorRemote
import com.mechamanul.composetmdb.remote.models.MovieDetailsRemote
import com.mechamanul.composetmdb.remote.models.PopularMoviesItems
import com.mechamanul.composetmdb.remote.services.MovieService
import com.mechamanul.utils.composetmdb.base.Result
import java.time.LocalDate
import javax.inject.Inject

//import com.mechamanul.utils.composetmdb.base.mapper.Mapper

class RemoteMovieDataSourceImpl @Inject constructor(
    private val movieService: MovieService
) : RemoteMovieDataSource {
    override suspend fun getPopularMovies(): Result<List<Movie>> {
        val response = movieService.getPopularMovies()

        if (response.isSuccessful) {
            val result =
                response.body()
            result?.items?.let {
                return Result.Success(it.map { fetchedMovieDetails -> fetchedMovieDetails.mapToDomain() })
            } ?: Result.Error(throwable = Throwable(result?.errorMessage))
        }
        return Result.Error(Throwable("Error with internet connection"))
    }

    override suspend fun getMovieDetails(id: String): Result<MovieDetails> {
        val response = movieService.getMovieDetails(id)

        if (response.isSuccessful) {
            val result = response.body()
            return result!!.errorMessage?.let {
                Result.Error(Throwable(it))
            } ?: Result.Success(result.mapToDomain())
        }
        return Result.Error(Throwable("Some error"))
    }


    private fun PopularMoviesItems.mapToDomain(): Movie {
        return Movie(
            id = this.id,
            name = this.title,
            year = this.year.toInt(),
            poster = this.image,
            crew = this.crew
        )
    }

    @Suppress("NewApi")
    private fun MovieDetailsRemote.mapToDomain(): MovieDetails {

        return MovieDetails(
            id = id,
            title = title,
            imagePath = imagePath,
            year = year.toInt(),
            // ISO as in IMDB API, no dateformatter
            releaseDate = LocalDate.parse(releaseDate),
            runtimeStr = runtimeStr,
            directors = directors,
            writers = writers,
            countries = countries,
            companies = companies,
            actorList = actorList.map { actorRemote: ActorRemote -> actorRemote.mapToDomain() },
            genres = genres,
            rating = rating.toFloat(),
            tagline = tagLine
        )
    }

    private fun ActorRemote.mapToDomain(): Actor {
        return Actor(id, name, imagePath, asCharacter)
    }
}