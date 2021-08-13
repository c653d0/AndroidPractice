package com.c653d0.word.db.WordList;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AllWordDao {
    @Insert
    public void insertWord(AllWord... words);

    @Update
    public void updateWord(AllWord... words);

    @Query("DELETE FROM ALLWORD")
    public void deleteAllWords();

    @Query("SELECT * FROM ALLWORD ORDER BY ID DESC")
    public LiveData<List<AllWord>> getAllWordLive();
}
