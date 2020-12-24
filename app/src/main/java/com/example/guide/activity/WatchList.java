package com.example.guide.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.guide.R;
import com.example.guide.adaptar.WatchListAdaptar;
import com.example.guide.local_db.AppDatabase;
import com.example.guide.local_db.Info;

import java.util.List;

public class WatchList extends AppCompatActivity {
        RecyclerView watchList;
        WatchListAdaptar watchListAdaptar;
        AppDatabase db;
        List<Info> info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_list);
        db = AppDatabase.getDbIntance(WatchList.this);
        watchList = findViewById(R.id.watchList);
        info = db.infoDao().getAll();
        watchList.setLayoutManager(new LinearLayoutManager(WatchList.this,LinearLayoutManager.HORIZONTAL,false));
        watchListAdaptar = new WatchListAdaptar(info,WatchList.this);
        watchList.setAdapter(watchListAdaptar);
    }
}