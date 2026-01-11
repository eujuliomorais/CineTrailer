package com.example.cinetrailer.data

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

data class MovieResponse(
    val results: List<Movie>
)

data class Movie(
    val id: Int,
    val title: String,
    val poster_path: String?,
    val overview: String?
) {
    val fullPosterUrl: String
        get() = "https://image.tmdb.org/t/p/w500$poster_path"
}
data class VideoResponse(
    val results: List<Video>
)

data class Video(
    val key: String,
    val site: String,
    val type: String
)

interface TmdbApiService {

    @GET("search/movie")
    suspend fun searchMovies(
        @Query("query") query: String
    ): MovieResponse

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(): MovieResponse

    @GET("movie/popular")
    suspend fun getPopularMovies(): MovieResponse

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(): MovieResponse

    @GET("movie/{movie_id}/videos")
    suspend fun getMovieVideos(
        @Path("movie_id") movieId: Int
    ): VideoResponse
}