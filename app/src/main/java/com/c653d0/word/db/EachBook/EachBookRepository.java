package com.c653d0.word.db.EachBook;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;

import java.util.List;

public class EachBookRepository {
    private LiveData<List<EachBook>> allEachBookLive;
    private EachBookDao eachBookDao;

    public EachBookRepository(Context context) {
        EachBookDataBase eachBookDataBase = EachBookDataBase.getDataBase(context.getApplicationContext());
        eachBookDao = eachBookDataBase.getEachBookDao();
        allEachBookLive = eachBookDao.getAllEachBookLive();
    }

    public void insertEachBook(EachBook... eachBooks) {
        new InsertASyncTask(eachBookDao).execute(eachBooks);
    }

    public void updateEachBook(EachBook... eachBooks) {
        new UpdateASyncTask(eachBookDao).execute(eachBooks);
    }

    public void deleteAllEachBook() {
        new DeleteASyncTask(eachBookDao).execute();
    }

    public LiveData<List<EachBook>> getAllEachBookLive() {
        return allEachBookLive;
    }


    static class InsertASyncTask extends AsyncTask<EachBook, Void, Void> {
        private EachBookDao eachBookDao;

        public InsertASyncTask(EachBookDao eachBookDao) {
            this.eachBookDao = eachBookDao;
        }

        @Override
        protected Void doInBackground(EachBook... eachBooks) {
            eachBookDao.insertBook(eachBooks);
            return null;
        }
    }

    static class UpdateASyncTask extends AsyncTask<EachBook, Void, Void> {
        EachBookDao eachBookDao;

        public UpdateASyncTask(EachBookDao eachBookDao) {
            this.eachBookDao = eachBookDao;
        }

        @Override
        protected Void doInBackground(EachBook... eachBooks) {
            eachBookDao.updateBook(eachBooks);
            return null;
        }
    }

    static class DeleteASyncTask extends AsyncTask<Void, Void, Void> {
        EachBookDao eachBookDao;

        public DeleteASyncTask(EachBookDao eachBookDao) {
            this.eachBookDao = eachBookDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            eachBookDao.deleteAll();
            return null;
        }
    }
}
