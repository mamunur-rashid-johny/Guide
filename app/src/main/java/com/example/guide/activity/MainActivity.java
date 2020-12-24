package com.example.guide.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guide.R;
import com.example.guide.adaptar.MovieAdaptar;
import com.example.guide.adaptar.TrendAdaptar;
import com.example.guide.adaptar.TvAdapter;
import com.example.guide.helper.Constant;
import com.example.guide.helper.NetworkReceiver;
import com.example.guide.local_db.AppDatabase;
import com.example.guide.model.movie.Movie;
import com.example.guide.model.movie.Result;
import com.example.guide.model.trending.Result_trend;
import com.example.guide.model.trending.Trend;
import com.example.guide.model.tv.Tv;
import com.example.guide.model.tv.TvResult;
import com.example.guide.webservices.ApiClient;
import com.example.guide.webservices.ApiInterface;

import java.util.List;

import ir.alirezabdn.wp7progress.WP10ProgressBar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    TextView appName,popmovie,poptv,trending;
    ApiInterface apiInterface;
    MovieAdaptar movieAdaptar;
    TvAdapter tvAdapter;
    TrendAdaptar trendAdaptar;
    RecyclerView movie_rec,tv_rec,trend_rec;
    List<Result> results;
    List<TvResult> tvResults;
    List<Result_trend> result_trends;
    WP10ProgressBar progressBar;
    ImageView bookmark;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initView();

        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        String text_main = "<font color=#2f3b39>WELCOME TO</font> <font color =#84bc6f> Guide</font>";
        appName.setText(Html.fromHtml(text_main));

        movie_rec.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false));
        tv_rec.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false));
        trend_rec.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false));

        if (NetworkReceiver.isConnected()){
            getMoviesList();
            getTvList();
            getTrendingList();
        }
        else {
            Toast.makeText(this,"Sorry! Not connected to internet", Toast.LENGTH_SHORT).show();
        }

        bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (db.infoDao().getAll()==null){
                    Toast.makeText(MainActivity.this,"Don't Have any Watch List!",Toast.LENGTH_SHORT).show();
                }
                else {
                    startActivity(new Intent(MainActivity.this,WatchList.class));
                }
            }
        });


    }

    private void initView() {
        movie_rec = findViewById(R.id.popularList);
        appName = findViewById(R.id.app_name);
        tv_rec = findViewById(R.id.popularT_list);
        trend_rec = findViewById(R.id.trendContent);
        popmovie = findViewById(R.id.pname);
        poptv = findViewById(R.id.tname);
        trending = findViewById(R.id.trendtitle);
        progressBar = findViewById(R.id.wp7progressBar);
        bookmark = findViewById(R.id.bookmark);
        db = AppDatabase.getDbIntance(MainActivity.this);
    }

    private void getTrendingList() {
        progressBar.showProgressBar();
        Call<Trend> call = apiInterface.getTrending(Constant.API_KEY);
        call.enqueue(new Callback<Trend>() {
            @Override
            public void onResponse(Call<Trend> call, Response<Trend> response) {
                progressBar.hideProgressBar();
                if (response.isSuccessful()){
                    result_trends = response.body().getResults();
                    trending.setText("Trending");
                    trendAdaptar = new TrendAdaptar(result_trends,MainActivity.this);
                    trend_rec.setAdapter(trendAdaptar);
                }
                else {
                    Toast.makeText(MainActivity.this,"can't load data",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Trend> call, Throwable t) {
                progressBar.hideProgressBar();
            }
        });
    }

    private void getTvList() {
        progressBar.showProgressBar();
        Call<Tv> call = apiInterface.getTvShow(Constant.API_KEY);
        call.enqueue(new Callback<Tv>() {
            @Override
            public void onResponse(Call<Tv> call, Response<Tv> response) {
                progressBar.hideProgressBar();
               if (response.isSuccessful()){
                   tvResults = response.body().getResults();
                   poptv.setText("Popular TV Shows");
                   tvAdapter = new TvAdapter(tvResults,MainActivity.this);
                   tv_rec.setAdapter(tvAdapter);
               }
               else {
                   Toast.makeText(MainActivity.this,"can't load data",Toast.LENGTH_SHORT).show();
               }
            }

            @Override
            public void onFailure(Call<Tv> call, Throwable t) {
            progressBar.hideProgressBar();
            }
        });

    }

    private void getMoviesList() {
        progressBar.showProgressBar();
        Call<Movie> call = apiInterface.getMovie(Constant.API_KEY);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                progressBar.hideProgressBar();
                if (response.isSuccessful()){
                    results = response.body().getResults();
                    popmovie.setText("Popular Movies");
                    Log.e("test case",results.get(0).getOriginalTitle() +results.get(0).getId().toString());
                    movieAdaptar = new MovieAdaptar(results,MainActivity.this);
                    movie_rec.setAdapter(movieAdaptar);
                }
                else {
                    Toast.makeText(MainActivity.this,"can't load data",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
            progressBar.hideProgressBar();
            }
        });
    }
}