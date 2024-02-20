package com.example.room.adaptors

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.room.Note
import com.example.room.R

class NoteAdaptor (val listener: OnClickListener) : RecyclerView.Adapter<NoteAdaptor.NoteViewHolder>() {
    var notesList: MutableList<Note> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false))
    }

    override fun getItemCount() = notesList.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notesList[position]
        holder.textTitle.text = note.title
        holder.textDescription.text = note.description
        holder.textPriority.text = note.priority.toString()
    }

    fun setNotes(notes: MutableList<Note>) {
        notesList = notes
        notifyDataSetChanged()
    }

    fun getNoteAt(position: Int): Note {
        return notesList[position]
    }

    inner class  NoteViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var textTitle = view.findViewById(R.id.text_view_title) as TextView
        var textDescription = view.findViewById(R.id.text_view_description) as TextView
        var textPriority = view.findViewById(R.id.text_view_priority) as TextView

        init {
            view.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    listener.onClickItem(notesList[adapterPosition])
                }
            }
        }
    }

    interface OnClickListener {
        fun onClickItem(note:Note)
    }
}