package com.example.guide.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Info.class},version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract InfoDao infoDao();
    public static AppDatabase INSTANCE;

    public static AppDatabase getDbIntance(Context context){
        if (INSTANCE==null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,"my_db")
                    .allowMainThreadQueries()
                    .build();
        }

        return INSTANCE;
    }
}
