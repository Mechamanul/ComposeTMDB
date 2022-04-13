package com.mechamanul.composetmdb.domain.usecase

import com.mechamanul.composetmdb.domain.models.Movie
import com.mechamanul.composetmdb.domain.repository.MovieRepository
import com.mechamanul.utils.composetmdb.base.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import java.time.LocalDate

class TestRepositoryWithMovies : MovieRepository {

    private val testResWithMovies =
        Result.Success(
            listOf(
                Movie(
                    "Shrek",
                    2000,
                    "Shrek movie", "pathToShrekPoster"
                ), Movie(
                    "Test",
                    2022,
                    "test movie", "pathToTestPoster"
                )
            )
        )

    override suspend fun getPopularMovies(): Flow<Result<List<Movie>>> {

        return flow {
            emit(testResWithMovies)
        }
    }
}

class TestRepositoryWithError : MovieRepository {

    private val testResError = Result.Error(Exception("network error"))
    override suspend fun getPopularMovies(): Flow<Result<List<Movie>>> {

        return flow {
            emit(testResError)
        }
    }
}

class TestRepositoryLoading : MovieRepository {


    override suspend fun getPopularMovies(): Flow<Result<List<Movie>>> {

        return flow {
            emit(Result.Loading)
        }
    }
}


//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GetPopularMoviesUseCaseImplTest {


    @Test
    fun `is correctly emits movies`() {
        val testRepository = TestRepositoryWithMovies()
        val getPopularMoviesUseCase = GetPopularMoviesUseCaseImpl(testRepository)
        runBlocking {
            val emittedValue = getPopularMoviesUseCase.invoke().first()
            val expected = Result.Success(
                listOf(
                    Movie(
                        "Shrek",
                        2000,
                        "Shrek movie", "pathToShrekPoster"
                    ), Movie(
                        "Test",
                        2022,
                        "test movie", "pathToTestPoster"
                    )
                )
            )
            assertEquals(emittedValue, expected)
        }
    }

    @Test
    fun `is correctly throws error`() {
        val testRepository = TestRepositoryWithError()
        val getPopularMoviesUseCase = GetPopularMoviesUseCaseImpl(testRepository)
        runBlocking {
            assertInstanceOf(Result.Error::class.java, getPopularMoviesUseCase.invoke().first())
        }
    }

    @Test
    fun `is correctly emits loading`() {
        val testRepository = TestRepositoryLoading()
        val getPopularMoviesUseCase = GetPopularMoviesUseCaseImpl(testRepository)
        runBlocking {
            assertInstanceOf(Result.Loading::class.java,getPopularMoviesUseCase.invoke().first())
        }

    }
}