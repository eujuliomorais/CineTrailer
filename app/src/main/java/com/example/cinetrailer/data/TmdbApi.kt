package com.example.cinetrailer.data

import retrofit2.http.GET
import retrofit2.http.Query

data class MovieResponse(
    val results: List<Movie>
)

data class Movie(
    val id: Int,
    val title: String,
    val poster_path: String?
) {
    val fullPosterUrl: String
        get() = "https://image.tmdb.org/t/p/w500$poster_path"
}

interface TmdbApiService {
    // Populares
    @GET("movie/popular")
    suspend fun getPopularMovies(): MovieResponse

    // Cartaz
    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(): MovieResponse

    //  em breve
    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(): MovieResponse

    // descobrir
    @GET("discover/movie")
    suspend fun getDiscoverMovies(
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("page") page: Int = 1
    ): MovieResponse
}