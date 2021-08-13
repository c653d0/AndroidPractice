package com.c653d0.word.db.BookList;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.c653d0.word.db.BookList.BookList;

import java.util.List;

@Dao
public interface BookListDao {
    @Insert
    public void insertBookList(BookList... books);

    @Update
    public void updateBookList(BookList... books);

    @Query("DELETE FROM BOOKLIST")
    public void deleteAllBook();

    @Query("SELECT * FROM BOOKLIST ORDER BY ID DESC")
    public LiveData<List<BookList>> getAllBookListLive();

}
