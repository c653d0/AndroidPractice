package com.c653d0.word.db.WordList;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = AllWord.class, version = 1, exportSchema = false)
public abstract class AllWordDataBase extends RoomDatabase {
    private static AllWordDataBase INSTANCE;

    static synchronized AllWordDataBase getDataBase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    AllWordDataBase.class, "word_list")
                    .build();
        }
        return INSTANCE;
    }

    public abstract AllWordDao getWordDao();
}
