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
import com.example.guide.R;
import com.example.guide.activity.Details;
import com.example.guide.helper.Constant;
import com.example.guide.local_db.AppDatabase;
import com.example.guide.local_db.Info;

import java.util.List;

public class WatchListAdaptar extends RecyclerView.Adapter<WatchListAdaptar.ViewHolder> {

    List<Info> info;
    Context context;
    AppDatabase db;

    public WatchListAdaptar(List<Info> info, Context context) {
        this.info = info;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.watch_list_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        db = AppDatabase.getDbIntance(context);
        holder.title.setText(info.get(position).getTitle());
        Glide.with(context).load(info.get(position).getPoster_path()).into(holder.poster);
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (info.get(position).getType().equals("tv")){

                }else {
                    context.startActivity(new Intent(context, Details.class).putExtra("id",String.valueOf(info.get(position).getInfo_id())));
                }
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Info d = info.get(holder.getAdapterPosition());
                db.infoDao().deleteUser(d);
                int position = holder.getAdapterPosition();
                info.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,info.size());

            }
        });
    }

    @Override
    public int getItemCount() {
        return info.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        ImageView poster,delete;
        RelativeLayout card;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.watchlistTitle);
            poster = itemView.findViewById(R.id.watchlistPoster);
            card = itemView.findViewById(R.id.wholeview);
            delete = itemView.findViewById(R.id.watchdelete);
        }
    }
}
