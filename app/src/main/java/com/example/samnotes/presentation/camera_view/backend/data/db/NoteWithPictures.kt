package com.example.samnotes.presentation.camera_view.backend.data.db

import androidx.room.Embedded
import androidx.room.Relation
import com.example.samnotes.features.data.local.entity.NoteEntity

data class  NoteWithPictures(
    @Embedded val note: NoteEntity,
    @Relation(
        parentColumn = "noteId",
        entityColumn = "noteOwnerId"
    )
    val pictures: List<PictureEntity>
)
