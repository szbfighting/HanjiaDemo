package com.example.roombasic;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Word {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "english_word")
    private String word;
    @ColumnInfo(name = "chinese_meaning")
    private String chinese;
    @ColumnInfo(name = "chinese_invisable")
    private boolean chineseInvisable;

    public boolean isChineseInvisable() {
        return chineseInvisable;
    }

    public void setChineseInvisable(boolean chineseInvisable) {
        this.chineseInvisable = chineseInvisable;
    }

    public Word(String word, String chinese) {
        this.word = word;
        this.chinese = chinese;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getChinese() {
        return chinese;
    }

    public void setChinese(String chinese) {
        this.chinese = chinese;
    }
}
