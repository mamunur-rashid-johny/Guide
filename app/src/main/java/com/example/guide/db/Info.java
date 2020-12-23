package com.example.guide.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Info {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name="info_id")
    public int info_id;
    @ColumnInfo(name = "title")
    public String title;
    @ColumnInfo(name="poster_path")
    public String poster_path;
}
