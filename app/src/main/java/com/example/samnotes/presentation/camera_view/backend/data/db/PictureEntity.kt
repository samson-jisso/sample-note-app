package com.example.samnotes.presentation.camera_view.backend.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PictureEntity(
    @PrimaryKey(autoGenerate = false)
    val pictureId : Int,
    val pictureAddress: String,
    val noteId: Int
)
