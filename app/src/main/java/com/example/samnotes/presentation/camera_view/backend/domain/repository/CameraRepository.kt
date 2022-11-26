package com.example.samnotes.presentation.camera_view.backend.domain.repository

import com.example.samnotes.presentation.camera_view.backend.data.db.NoteWithPictures
import com.example.samnotes.presentation.camera_view.backend.data.db.PicturesEntity


interface CameraRepository {
    suspend fun insertPictureWithNote(picturesEntity: PicturesEntity)
    suspend fun getNotePicture(noteId:Int):NoteWithPictures
}