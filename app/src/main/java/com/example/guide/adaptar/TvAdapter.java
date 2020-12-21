package com.example.guide.adaptar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.guide.R;
import com.example.guide.helper.Constant;
import com.example.guide.model.TvResult;

import java.util.List;

public class TvAdapter extends RecyclerView.Adapter<TvAdapter.ViewHolder> {
    List<TvResult> tvResults;
    Context context;

    public TvAdapter(List<TvResult> tvResults, Context context) {
        this.tvResults = tvResults;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(tvResults.get(position).getName());
        holder.date.setText(tvResults.get(position).getFirstAirDate());
        holder.vote.setText(tvResults.get(position).getVoteCount().toString());
        Glide.with(context).load(Constant.IMAGE_URL+tvResults.get(position).getPosterPath()).into(holder.poster);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,tvResults.get(position).getName(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return tvResults.size();
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
