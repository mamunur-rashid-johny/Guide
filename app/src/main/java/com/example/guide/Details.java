package com.example.guide;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.guide.helper.Constant;
import com.example.guide.model.detail_movie.Genre;
import com.example.guide.model.detail_movie.MovieDetail;
import com.example.guide.webservices.ApiClient;
import com.example.guide.webservices.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Details extends AppCompatActivity {

    int movie_id;
    ApiInterface apiInterface;
    ImageView backdrop,dposter,backimage;
    TextView dtitle,rating,runtime,r_date,dover,dgenre;
    String dGenre = "Genre: ";
    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        backdrop = findViewById(R.id.dbPoster);
        dposter = findViewById(R.id.dPoster);
        dtitle = findViewById(R.id.dTitle);
        rating = findViewById(R.id.rating);
        runtime = findViewById(R.id.runTime);
        r_date = findViewById(R.id.release_date);
        dover = findViewById(R.id.doverview);
        dgenre = findViewById(R.id.dgenre);
        backimage = findViewById(R.id.backImage);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        movie_id = Integer.parseInt(getIntent().getStringExtra("id"));
       getDetail();

       backimage.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               onBackPressed();
           }
       });


    }

    private void getDetail() {

        Call<MovieDetail> call = apiInterface.getMovieDetail(movie_id,Constant.API_KEY);
        call.enqueue(new Callback<MovieDetail>() {
            @Override
            public void onResponse(Call<MovieDetail> call, Response<MovieDetail> response) {
                if (response.isSuccessful()){
                    Glide.with(Details.this).load(Constant.Back_DROP_IMAGE+response.body().getBackdropPath()).into(backdrop);
                    Glide.with(Details.this).load(Constant.Back_DROP_IMAGE+response.body().getPosterPath()).into(dposter);
                    dtitle.setText(response.body().getTitle());
                    rating.setText(response.body().getVoteAverage().toString());
                    runtime.setText("\u2022 "+response.body().getRuntime().toString());
                    r_date.setText("\u2022 "+response.body().getReleaseDate());
                    dover.setText(response.body().getOverview());
                    for (Genre genre:response.body().getGenres()){
                        dGenre =dGenre+" "+genre.getName();
                        i++;
                        if (response.body().getGenres().size() != i){
                            dGenre = dGenre+", ";
                        }
                        else {
                            dGenre = dGenre+".";
                        }
                    }
                    dgenre.setText(dGenre);
                }
            }

            @Override
            public void onFailure(Call<MovieDetail> call, Throwable t) {

            }
        });
    }

}