package com.example.notesapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notesapp.data.NoteData

@Database(entities = arrayOf(NoteData:: class), version = 1)
abstract class NotesDatabase: RoomDatabase() {
    abstract val noteDao: NoteDao

    companion object{
        @Volatile  // any change in instance will be available for any other thread
        var INSTANCE: NotesDatabase? = null

        @Synchronized
        fun getDatabaseInstance(context: Context): NotesDatabase{
            if(INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context,
                    NotesDatabase::class.java,
                    "notes_db"
                ).fallbackToDestructiveMigration().build()
            }
            return INSTANCE!!
        }
    }
}