package com.example.notesapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp.data.NoteData
import com.example.notesapp.repository.NotesRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class NotesViewModel(
    private val notesRepo: NotesRepo
): ViewModel() {

    val notes = notesRepo.getNotes()
    private val _searchNotes = MutableStateFlow<List<NoteData>>(emptyList())
    val searchNotes: StateFlow<List<NoteData>> = _searchNotes

    fun insertAndUpdateNote(note: NoteData) = viewModelScope.launch {
        notesRepo.insertAndUpdateNote(note)
    }

    fun deleteNote(note: NoteData) = viewModelScope.launch {
        notesRepo.deleteNote(note)
    }

    fun deleteAllNotes() = viewModelScope.launch {
        notesRepo.deleteAllNotes()
    }

    fun searchNotes(searchQuery: String) = viewModelScope.launch {
        notesRepo.searchNotes(searchQuery).collect {
                notesList -> _searchNotes.emit(notesList)
        }
    }

}