package com.example.room

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

interface NoteDao {
    @Insert
    suspend fun insert(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Update
    suspend fun update(note: Note)

    @Query("DELETE FROM note_table")
    fun deleteAllNote()

    @Query("SELECT * FROM NOTE_TABLE ORDER BY priority ASC")
    fun getAllNotes(): LiveData<MutableList<Note>>
}