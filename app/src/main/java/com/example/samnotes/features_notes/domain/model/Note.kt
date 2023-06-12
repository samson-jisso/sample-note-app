package com.example.samnotes.features_notes.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    val title: String,
    val content: String,
    val photoUri:String,
    @PrimaryKey val id:Int? = null
)

class  InvalidNoteException(message:String):Exception(message)
