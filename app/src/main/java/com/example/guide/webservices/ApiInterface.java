package com.example.guide.webservices;

import com.example.guide.model.movie.Movie;
import com.example.guide.model.trending.Trend;
import com.example.guide.model.tv.Tv;
import com.example.guide.model.detail_movie.MovieDetail;
import com.example.guide.model.tv_detail.TvDeatilModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    //here api interface goes..

    @GET("movie/popular")
    Call<Movie> getMovie(@Query("api_key") String api_key);

    @GET("tv/popular")
    Call<Tv> getTvShow(@Query("api_key") String api_key);

    @GET("trending/all/day")
    Call<Trend> getTrending(@Query("api_key") String api_key);

    @GET("movie/{id}")
    Call<MovieDetail> getMovieDetail(@Path("id") int id,@Query("api_key") String api_key);

    @GET("tv/{id}")
    Call<TvDeatilModel> getTv(@Path("id") int id, @Query("api_key") String api_key);
}
