package com.example.notesapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.notesapp.data.NoteData
import com.example.notesapp.databinding.NotesItemBinding

class NotesAdapter: RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {
    inner class NotesViewHolder(private val binding: NotesItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(note: NoteData){
            binding.tvNoteTitle.text = note.noteTitle
        }
    }

    private val diffCallBack = object: DiffUtil.ItemCallback<NoteData>(){

        override fun areItemsTheSame(oldItem: NoteData, newItem: NoteData): Boolean {
            return oldItem.noteId == newItem.noteId
        }

        override fun areContentsTheSame(oldItem: NoteData, newItem: NoteData): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this,diffCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(
            NotesItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val note = differ.currentList[position]
        holder.bind(note)

        holder.itemView.setOnClickListener {
            onClick?.invoke(note)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    var  onClick: ((NoteData) -> Unit) ?= null
}