package com.example.guide;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.guide.R;
import com.example.guide.helper.Constant;
import com.example.guide.model.detail_movie.Genre;
import com.example.guide.model.tv_detail.TvDeatilModel;
import com.example.guide.webservices.ApiClient;
import com.example.guide.webservices.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Tv_Detail extends AppCompatActivity {
        String id ;
        ApiInterface apiInterface;
        ImageView tposter;
        TextView t_title,t_rating,t_season,t_date,t_overview,t_genre;
        TvDeatilModel tvDeatilModel;
    String dGenre = "Genre: ";
    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv__detail);
        tposter = findViewById(R.id.tdPoster);
        t_title = findViewById(R.id.dtTitle);
        t_rating = findViewById(R.id.trating);
        t_season = findViewById(R.id.tseason);
        t_date = findViewById(R.id.trelease_date);
        t_overview = findViewById(R.id.tdoverview);
        t_genre = findViewById(R.id.tdgenre);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);


        id = getIntent().getStringExtra("tv_id");
        Log.e("test case",getIntent().getStringExtra("tv_id"));
        getTvDetail();

    }

    private void getTvDetail() {
        Call<TvDeatilModel> call = apiInterface.getTv(Integer.parseInt(id), Constant.API_KEY);
        call.enqueue(new Callback<TvDeatilModel>() {
            @Override
            public void onResponse(Call<TvDeatilModel> call, Response<TvDeatilModel> response) {
                if (response.isSuccessful()){
                    tvDeatilModel = response.body();
                    Glide.with(Tv_Detail.this).load(Constant.TV_IMAGE_URL+tvDeatilModel.posterPath)
                            .apply(new RequestOptions().placeholder(R.drawable.no_ads)).into(tposter);
                    t_title.setText(tvDeatilModel.originalName);
                    t_rating.setText(tvDeatilModel.voteAverage.toString());
                    t_season.setText(tvDeatilModel.seasons.size());
                    if (tvDeatilModel.lastAirDate !=null){
                        t_date.setText(tvDeatilModel.firstAirDate.split("-")[0]+"-"+tvDeatilModel.lastAirDate.split("-")[0]);
                    }
                    else {
                        t_date.setText(tvDeatilModel.firstAirDate.split("-")[0]);
                    }
                    t_overview.setText(tvDeatilModel.overview);
                    for (TvDeatilModel.Genre genre:tvDeatilModel.genres){
                        dGenre =dGenre+" "+genre.name;
                        i++;
                        if (tvDeatilModel.genres.size() != i){
                            dGenre = dGenre+", ";
                        }
                        else {
                            dGenre = dGenre+".";
                        }
                    }
                    t_genre.setText(dGenre);

                }
            }

            @Override
            public void onFailure(Call<TvDeatilModel> call, Throwable t) {

            }
        });
    }
}