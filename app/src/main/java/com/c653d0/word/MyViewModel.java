package com.c653d0.word;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.c653d0.word.db.BookList.BookList;
import com.c653d0.word.db.BookList.BookListRepository;
import com.c653d0.word.db.EachBook.EachBook;
import com.c653d0.word.db.EachBook.EachBookRepository;
import com.c653d0.word.db.WordList.AllWord;
import com.c653d0.word.db.WordList.WordRepository;

import java.util.List;

public class MyViewModel extends AndroidViewModel {
    BookListRepository bookListRepository;
    EachBookRepository eachBookRepository;
    WordRepository wordRepository;


    public MyViewModel(@NonNull Application application) {
        super(application);
        bookListRepository =new BookListRepository(application);
        eachBookRepository = new EachBookRepository(application);
        wordRepository = new WordRepository(application);
    }


    //BookList
    public LiveData<List<BookList>> getAllBookLive() {
        return bookListRepository.getAllBookLive();
    }
    public void insertBook(BookList... books) {
        bookListRepository.insertBook(books);
    }
    public void updateBook(BookList... books) {
        bookListRepository.updateBook(books);
    }
    public void deleteBook() {
        bookListRepository.deleteBook();
    }

    //EachBook
    public void insertEachBook(EachBook... eachBooks) {
        eachBookRepository.insertEachBook(eachBooks);
    }
    public void updateEachBook(EachBook... eachBooks) {
        eachBookRepository.updateEachBook(eachBooks);
    }
    public void deleteEachBook() {
        eachBookRepository.deleteAllEachBook();
    }
    public LiveData<List<EachBook>> getAllEachBookLive() {
        return eachBookRepository.getAllEachBookLive();
    }

    //Word
    public void insertWord(AllWord... words) {
        wordRepository.insertWord(words);
    }
    public void updateWord(AllWord... words) {
        wordRepository.updateWord(words);
    }
    public void deleteWord() {
        wordRepository.deleteWord();
    }
    public LiveData<List<AllWord>> getAllWordLive() {
        return wordRepository.getAllWordLive();
    }
}
