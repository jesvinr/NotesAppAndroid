package com.example.notesapp.repository

import com.example.notesapp.data.NoteData
import com.example.notesapp.data.database.NotesDatabase

class NotesRepo(notesDatabase: NotesDatabase) {

    val notesDao = notesDatabase.noteDao

    suspend fun insertAndUpdateNote(note: NoteData) = notesDao.insertAndUpdateNote(note)

    suspend fun deleteNote(note: NoteData) = notesDao.deleteNote(note)

    fun getNotes() = notesDao.selectNotes()

    fun searchNotes(searchQuery: String) = notesDao.searchNotesTitle(searchQuery)

    suspend fun deleteAllNotes() =  notesDao.deleteAllNotes()
}