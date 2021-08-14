package com.c653d0.room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class WordRepository {
    private LiveData<List<Word>> allWordsLive;
    private WordDao wordDao;

    public WordRepository(Context context) {
        WordDataBase wordDataBase = WordDataBase.getDatabase(context.getApplicationContext());
        wordDao = wordDataBase.getWordDao();
        allWordsLive = wordDao.getAllWordsLive();
    }

    void insertWords(Word... words) {
        new InsertAsyncTask(wordDao).execute(words);
    }

    void updateWords(Word... words) {
        new UpdateAsyncTask(wordDao).execute(words);
    }

    void deleteWords(Word... words) {
        new DeleteAsyncTask(wordDao).execute(words);
    }

    void deleteAllWords() {
        new DeleteAllAsyncTask(wordDao).execute();
    }

    public LiveData<List<Word>> getAllWordsLive() {
        return allWordsLive;
    }

    static class InsertAsyncTask extends AsyncTask<Word, Void, Void> {
        private WordDao wordDao;

        public InsertAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        //在后台操作，不在主线程操作
        protected Void doInBackground(Word... words) {
            wordDao.insertWords(words);
            return null;
        }

        //任务完成时呼叫，将结果带回主线程
       /* @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }*/

        //当进度发生更新时会呼叫
        /*@Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }*/

        //后台任务执行之前会呼叫
        /*@Override
        protected void onPreExecute() {
            super.onPreExecute();
        }*/
    }

    static class UpdateAsyncTask extends AsyncTask<Word, Void, Void> {
        private WordDao wordDao;

        public UpdateAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        //在后台操作，不在主线程操作
        protected Void doInBackground(Word... words) {
            wordDao.updateWords(words);
            return null;
        }

    }

    static class DeleteAsyncTask extends AsyncTask<Word, Void, Void> {
        private WordDao wordDao;

        public DeleteAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        //在后台操作，不在主线程操作
        protected Void doInBackground(Word... words) {
            wordDao.deleteWords(words);
            return null;
        }

    }

    static class DeleteAllAsyncTask extends AsyncTask<Void, Void, Void> {
        private WordDao wordDao;

        public DeleteAllAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        //在后台操作，不在主线程操作
        protected Void doInBackground(Void... voids) {
            wordDao.deleteAllWords();
            return null;
        }

    }
}
