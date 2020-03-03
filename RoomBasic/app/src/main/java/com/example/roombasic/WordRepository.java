package com.example.roombasic;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class WordRepository {
    private LiveData<List<Word>> allWordsLive;
    private WordDao wordDao;

    public LiveData<List<Word>> getAllWordsLive(){
        return allWordsLive;
    }

    public LiveData<List<Word>> findWordWithPattern(String pattern){
        return wordDao.findWordWithPattern("%"+pattern+"%");
    }

    public WordRepository(Context context) {
        WordDatabase wordDatabase = WordDatabase.getInstance(context.getApplicationContext());
        wordDao = wordDatabase.getWordDao();
        allWordsLive = wordDao.getAllWordsLive();
    }

    public void insert(Word...words){
        new InsertAsyncTask(wordDao).execute(words);
    }

    public void update(Word...words){
        new UpdateAsyncTask(wordDao).execute(words);
    }

    public void delete(Word...words){
        new DeleteAsyncTask(wordDao).execute(words);
    }

    public void deleteAll(){
        new ClearAsyncTask(wordDao).execute();
    }

    static class InsertAsyncTask extends AsyncTask<Word,Void,Void> {
        private WordDao wordDao;

        public InsertAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            wordDao.insertWords(words);
            return null;
        }
    }

    static class DeleteAsyncTask extends AsyncTask<Word,Void,Void> {
        private WordDao wordDao;

        public DeleteAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            wordDao.deleteWords(words);
            return null;
        }
    }

    static class ClearAsyncTask extends AsyncTask<Void,Void,Void> {
        private WordDao wordDao;

        public ClearAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            wordDao.deleteAll();
            return null;
        }
    }

    static class UpdateAsyncTask extends AsyncTask<Word,Void,Void> {
        private WordDao wordDao;

        public UpdateAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            wordDao.updateWords(words);
            return null;
        }
    }
}
