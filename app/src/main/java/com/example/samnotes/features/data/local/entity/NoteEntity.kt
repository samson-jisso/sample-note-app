package com.example.samnotes.features.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.samnotes.features.domain.model.Note

@Entity
data class NoteEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int? = null,
    val title: String? = null,
    val content: String? = null,
) {
    fun toNoteDomain(): Note = Note(
        id = id,
        title = title,
        content = content,
    )
}
