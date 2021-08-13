package com.c653d0.word.db.BookList;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = BookList.class, version = 1, exportSchema = false)
public abstract class BookListDataBase extends RoomDatabase {
    private static BookListDataBase INSTANCE;

    static synchronized BookListDataBase getDataBase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    BookListDataBase.class, "book_list")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }

    public abstract BookListDao getBookListDao();
}
