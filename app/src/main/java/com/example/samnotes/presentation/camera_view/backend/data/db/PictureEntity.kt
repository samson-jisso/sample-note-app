package com.example.samnotes.presentation.camera_view.backend.data.db

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.samnotes.features.data.local.entity.NoteEntity

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = NoteEntity::class,
            parentColumns = arrayOf("noteId"),
            childColumns = arrayOf("noteOwnerId"),
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class PictureEntity(
    @PrimaryKey(autoGenerate = false)
    val pictureId: Int,
    val pictureAddress: String,
    val noteOwnerId: Int,
    val offsetX: String? = "0",
    val offsetY: String? = "0"
)
