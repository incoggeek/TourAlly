package com.app.tour_ally;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

@Database(entities = Notes.class, exportSchema = false, version = 1)
public abstract class DatabaseHelper extends RoomDatabase {

    private static final String DB_NAME = "notes_db";
    private static DatabaseHelper instance;

    public static synchronized DatabaseHelper getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    DatabaseHelper.class,DB_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract NotesDao notesDao();
}