package com.c653d0.word.db.WordList;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;


import java.util.List;

public class WordRepository {
    private AllWordDao wordDao;
    private LiveData<List<AllWord>> allWordLive;

    public WordRepository(Context context) {
        AllWordDataBase wordDataBase = AllWordDataBase.getDataBase(context.getApplicationContext());
        Log.e("getDataBAse",wordDataBase.toString());
        wordDao = wordDataBase.getWordDao();
        allWordLive = wordDao.getAllWordLive();
    }

    public void insertWord(AllWord... words){
        new InsertASyncTask(wordDao).execute(words);
    }

    public void updateWord(AllWord... words){
        new UpdateAsyncTask(wordDao).execute(words);
    }

    public void deleteWord() {
        new DeleteAsyncTask(wordDao).execute();
    }

    public LiveData<List<AllWord>> getAllWordLive(){
        return allWordLive;
    }


    static class InsertASyncTask extends AsyncTask<AllWord,Void,Void> {
        AllWordDao wordDao;

        public InsertASyncTask(AllWordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(AllWord... words) {
            wordDao.insertWord(words);
            return null;
        }
    }

    static class UpdateAsyncTask extends AsyncTask<AllWord, Void, Void> {
        private AllWordDao wordDao;

        public UpdateAsyncTask(AllWordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(AllWord... words) {
            wordDao.updateWord(words);
            return null;
        }
    }

    static class DeleteAsyncTask extends AsyncTask<Void, Void, Void>{
        private AllWordDao wordDao;

        public DeleteAsyncTask(AllWordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            wordDao.deleteAllWords();
            return null;
        }
    }
}
