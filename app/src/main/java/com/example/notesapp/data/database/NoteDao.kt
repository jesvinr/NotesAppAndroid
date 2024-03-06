package com.example.notesapp.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.notesapp.data.NoteData
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAndUpdateNote(note: NoteData)

//    At @insert (onConflict = OnConflictStrategy.REPLACE) updates the notes in db
//    @Update
//    suspend fun updateNote(note: NoteData)

    @Delete
    suspend fun deleteNote(note: NoteData)

    // Table name => NoteData
    @Query("SELECT * FROM NoteData ORDER BY noteId DESC")
    fun selectNotes(): Flow<List<NoteData>>

    @Query("SELECT * FROM NoteData WHERE noteTitle LIKE '%' || :searchQuery || '%'")
    fun searchNotesTitle(searchQuery: String): Flow<List<NoteData>>

    @Query("DELETE FROM NoteData")
    suspend fun deleteAllNotes()
}