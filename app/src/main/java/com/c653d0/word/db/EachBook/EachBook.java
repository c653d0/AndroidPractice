package com.c653d0.word.db.EachBook;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class EachBook {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "class_id")
    private String classId;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "word_num")
    private String wordNum;
    @ColumnInfo(name = "is_book")
    private boolean isBook;

    public EachBook(String classId, String title, String wordNum, boolean isBook) {
        this.classId = classId;
        this.title = title;
        this.wordNum = wordNum;
        this.isBook = isBook;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWordNum() {
        return wordNum;
    }

    public void setWordNum(String wordNum) {
        this.wordNum = wordNum;
    }

    public boolean isBook() {
        return isBook;
    }

    public void setBook(boolean book) {
        isBook = book;
    }
}
