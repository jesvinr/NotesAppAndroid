package com.example.notesapp.viewmodel.providerfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.notesapp.repository.NotesRepo
import com.example.notesapp.viewmodel.NotesViewModel

class NotesViewModelProviderFactory
    (private val notesRepo: NotesRepo) :ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NotesViewModel(notesRepo) as T
    }
}