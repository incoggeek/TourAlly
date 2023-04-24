package com.app.tour_ally;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
/*
Data Object Access interface to perform different
operations on notes
*/
@Dao
public interface NotesDao {
    @Query("select * from notes")
    List<Notes> getNotes();

    @Insert
    void addNote(Notes notes);

    @Delete
    void deleteNotes(Notes notes);

}
