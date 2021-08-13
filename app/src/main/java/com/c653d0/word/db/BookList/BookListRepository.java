package com.c653d0.word.db.BookList;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class BookListRepository {
    private LiveData<List<BookList>> allBookLive;
    BookListDao bookListDao;


    public BookListRepository(Context context) {
        BookListDataBase bookListDataBase = BookListDataBase.getDataBase(context.getApplicationContext());
        bookListDao = bookListDataBase.getBookListDao();
        allBookLive = bookListDao.getAllBookListLive();
    }

    public void insertBook(BookList... books) {
        new InsertAsyncTask(bookListDao).execute(books);
    }
    public void updateBook(BookList... books) {
        new UpdateAsyncTask(bookListDao).execute(books);
    }
    public void deleteBook() {
        new DeleteAsyncTask(bookListDao).execute();
    }

    public LiveData<List<BookList>> getAllBookLive(){
        return allBookLive;
    }


    static class InsertAsyncTask extends AsyncTask<BookList, Void, Void> {
        private BookListDao bookListDao;

        public InsertAsyncTask(BookListDao bookListDao) {
            this.bookListDao = bookListDao;
        }

        @Override
        //在后台操作，不在主线程操作
        protected Void doInBackground(BookList... books) {
            bookListDao.insertBookList(books);
            return null;
        }
    }

    static class UpdateAsyncTask extends AsyncTask<BookList, Void, Void> {
        private BookListDao bookListDao;

        public UpdateAsyncTask(BookListDao bookListDao) {
            this.bookListDao = bookListDao;
        }

        @Override
        protected Void doInBackground(BookList... bookLists) {
            bookListDao.updateBookList(bookLists);
            return null;
        }
    }

    static class DeleteAsyncTask extends AsyncTask<Void, Void, Void> {
        private BookListDao bookListDao;

        public DeleteAsyncTask(BookListDao bookListDao) {
            this.bookListDao = bookListDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            bookListDao.deleteAllBook();
            return null;
        }
    }

}
