package com.example.pagingdemo;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Student.class,version = 1,exportSchema = false)
public abstract class StudentDataBase extends RoomDatabase {
    static StudentDataBase instance;

    static synchronized StudentDataBase getInstance(Context context){
        if (instance==null){
            instance = Room.databaseBuilder(context,StudentDataBase.class,"students_database").build();
        }

        return instance;
    }

    public abstract StudentDao getStudentDao();
}
