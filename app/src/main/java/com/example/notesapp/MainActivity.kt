package com.example.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.notesapp.data.database.NotesDatabase
import com.example.notesapp.repository.NotesRepo
import com.example.notesapp.viewmodel.NotesViewModel
import com.example.notesapp.viewmodel.providerfactory.NotesViewModelProviderFactory

class MainActivity : AppCompatActivity() {
    val viewModel: NotesViewModel by lazy {
        val database = NotesDatabase.getDatabaseInstance(this)
        val notesRepo = NotesRepo(database)
        val notesProviderFactory = NotesViewModelProviderFactory(notesRepo)
        ViewModelProvider(this, notesProviderFactory)[NotesViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}