package com.example.samnotes.features.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NoteEntity(
 @PrimaryKey
 val id: String,
 val title:String,
 val content: String,
)
