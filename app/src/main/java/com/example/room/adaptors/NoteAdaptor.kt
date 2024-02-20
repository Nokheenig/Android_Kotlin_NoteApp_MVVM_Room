package com.example.room.adaptors

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.room.Note
import com.example.room.R

class NoteAdaptor (val listener: OnClickListener) :
    androidx.recyclerview.widget.ListAdapter<Note, NoteAdaptor.NoteViewHolder>(DIFF_CALLBACK) {

        companion object {
            private val DIFF_CALLBACK = object: DiffUtil.ItemCallback<Note>() {
                override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
                    return oldItem == newItem
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false))
    }


    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = getItem(position)
        holder.textTitle.text = note.title
        holder.textDescription.text = note.description
        holder.textPriority.text = note.priority.toString()
    }


    fun getNoteAt(position: Int): Note {
        return getItem(position)
    }

    inner class  NoteViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var textTitle = view.findViewById(R.id.text_view_title) as TextView
        var textDescription = view.findViewById(R.id.text_view_description) as TextView
        var textPriority = view.findViewById(R.id.text_view_priority) as TextView

        init {
            view.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    listener.onClickItem(getItem(adapterPosition))
                }
            }
        }
    }

    interface OnClickListener {
        fun onClickItem(note:Note)
    }
}