package com.example.swiggy_test

import com.example.swiggy_test.data.model.Movie
import com.example.swiggy_test.data.model.MovieDetail
import com.example.swiggy_test.data.model.MovieDetailData
import com.example.swiggy_test.data.model.MovieDetailResponse
import com.example.swiggy_test.data.model.MovieListData
import com.example.swiggy_test.data.model.MovieListResponse
import com.example.swiggy_test.data.remote.MovieApiService
import com.example.swiggy_test.data.repository.MovieRepositoryImpl
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class MovieRepositoryTest {

    private val fakeApiService = object : MovieApiService {
        override suspend fun getMovies(page: Int, limit: Int): MovieListResponse {
            return MovieListResponse(
                status = "ok",
                data = MovieListData(
                    movieCount = 1,
                    pageNumber = page,
                    movies = listOf(
                        Movie(id = 1, title = "Test Movie", year = 2026, rating = 8.5)
                    )
                )
            )
        }

        override suspend fun getMovieDetails(movieId: Int): MovieDetailResponse {
            return if (movieId == 1) {
                MovieDetailResponse(
                    status = "ok",
                    data = MovieDetailData(
                        movie = MovieDetail(id = 1, title = "Test Movie Detail", rating = 8.5)
                    )
                )
            } else {
                MovieDetailResponse(status = "error", data = null)
            }
        }
    }

    private val repository = MovieRepositoryImpl(fakeApiService)

    @Test
    fun getMovieDetails_success() = runBlocking {
        val result = repository.getMovieDetails(1)
        assertTrue(result.isSuccess)
        assertEquals("Test Movie Detail", result.getOrNull()?.title)
    }

    @Test
    fun getMovieDetails_notFound_failure() = runBlocking {
        val result = repository.getMovieDetails(999)
        assertTrue(result.isFailure)
    }
}
