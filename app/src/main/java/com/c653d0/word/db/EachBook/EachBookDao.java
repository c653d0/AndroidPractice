package com.c653d0.word.db.EachBook;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface EachBookDao {
    @Insert
    public void insertBook(EachBook... books);

    @Update
    public void updateBook(EachBook... books);

    @Query("DELETE FROM EachBook")
    public void deleteAll();

    @Query("SELECT * FROM EachBook ORDER BY ID DESC")
    public LiveData<List<EachBook>> getAllEachBookLive();
}
