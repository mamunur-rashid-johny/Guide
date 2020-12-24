package com.example.guide.local_db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface InfoDao {

    @Query("SELECT * FROM info")
    List<Info> getAll();

    @Insert()
    void insertInfo(Info info);

    @Delete
    void deleteUser(Info info);

    @Query("SELECT * FROM info WHERE info_id=:info_id ")
    int getData(int info_id);


}
