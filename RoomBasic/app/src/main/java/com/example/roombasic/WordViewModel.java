package com.example.roombasic;

import android.app.Application;
import android.os.AsyncTask;
import android.os.strictmode.WebViewMethodCalledOnWrongThreadViolation;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class WordViewModel extends AndroidViewModel {
    private WordRepository repository;
    public WordViewModel(@NonNull Application application) {
        super(application);
        repository = new WordRepository(application);
    }

    public LiveData<List<Word>> getAllWord(){
        return repository.getAllWordsLive();
    }

    public void insert(Word...words){
        repository.insert(words);
    }

    public void update(Word...words){
        repository.update(words);
    }

    public void delete(Word...words){
        repository.delete(words);
    }

    public void deleteAll(){
        repository.deleteAll();
    }

    public LiveData<List<Word>> findWordWithPattern(String pattern){
        return repository.findWordWithPattern(pattern);
    }
}
