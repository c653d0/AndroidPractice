package com.c653d0.word.db.EachBook;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = EachBook.class,version = 1,exportSchema = false)
public abstract class EachBookDataBase extends RoomDatabase{
    private static EachBookDataBase INSTANCE;

    static synchronized EachBookDataBase getDataBase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    EachBookDataBase.class,"each_book")
                    .build();
        }
        return INSTANCE;
    }

    public abstract EachBookDao getEachBookDao();
}
