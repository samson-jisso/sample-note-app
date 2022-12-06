package com.example.samnotes.features.domain.model

import com.example.samnotes.features.data.local.entity.NoteEntity

data class Note(
    val noteId:Int?,
    val title: String?,
    val content:String?,
){
    fun toNoteEntity():NoteEntity {
        return NoteEntity(
            noteId = noteId,
            title = title,
            content = content
        )
    }
}
