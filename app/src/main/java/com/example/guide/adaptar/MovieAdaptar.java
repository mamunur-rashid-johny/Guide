package com.example.guide.adaptar;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.guide.activity.Details;
import com.example.guide.R;
import com.example.guide.helper.Constant;
import com.example.guide.model.movie.Result;

import java.util.List;

public class MovieAdaptar extends RecyclerView.Adapter<MovieAdaptar.ViewHolder>{
    List<Result> results;
    Context context;

    public MovieAdaptar(List<Result> results, Context context) {
        this.results = results;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.title.setText(results.get(position).getOriginalTitle());
        if (results.get(position).getReleaseDate().equals("")){
            holder.date.setText("Not Available");
        }
        else {
            holder.date.setText(results.get(position).getReleaseDate());
        }
        holder.vote.setText(results.get(position).getVoteCount().toString());
        Glide.with(context).load(Constant.IMAGE_URL+results.get(position).getPosterPath()).into(holder.poster);


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context,results.get(position).getId().toString(),Toast.LENGTH_SHORT).show();
                context.startActivity(new Intent(context, Details.class).putExtra("id",results.get(position).getId().toString()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,date,vote;
        ImageView poster;
        RelativeLayout cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.mTitle);
            date = itemView.findViewById(R.id.r_date);
            vote = itemView.findViewById(R.id.vote_count);
            poster = itemView.findViewById(R.id.poster);
            cardView = itemView.findViewById(R.id.cardview);
        }
    }
}
