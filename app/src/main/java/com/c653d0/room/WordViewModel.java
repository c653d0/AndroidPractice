package com.c653d0.room;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class WordViewModel extends AndroidViewModel {
//    private WordDao wordDao;
//    private LiveData<List<Word>> allWordsLive;
    private WordRepository wordrepository;

    public WordViewModel(@NonNull Application application) {
        super(application);
        wordrepository = new WordRepository(application);
    }

    public LiveData<List<Word>> getAllWordsLive() {
        return wordrepository.getAllWordsLive();
    }

    void insertWords(Word... words) {
        wordrepository.insertWords(words);
    }

    void updateWords(Word... words) {
        wordrepository.updateWords(words);
    }

    void deleteWords(Word... words) {
        wordrepository.deleteWords(words);
    }

    void deleteAllWords() {
        wordrepository.deleteAllWords();
    }

}
