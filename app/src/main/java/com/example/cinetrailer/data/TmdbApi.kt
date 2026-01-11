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
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String
    ): MovieResponse

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("api_key") apiKey: String
    ): MovieResponse

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("api_key") apiKey: String
    ): MovieResponse

    @GET("discover/movie")
    suspend fun getDiscoverMovies(
        @Query("api_key") apiKey: String,
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("page") page: Int = 1
    ): MovieResponse

    @GET("search/movie")
    suspend fun searchMovies(
        @Query("api_key") apiKey: String,
        @Query("query") query: String,
        @Query("language") language: String = "pt-BR"
    ): MovieResponse
}