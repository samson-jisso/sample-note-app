package com.example.samnotes.presentation.camera_view.backend.domain.repository

import com.example.samnotes.presentation.camera_view.backend.data.db.NoteWithPictures
import com.example.samnotes.presentation.camera_view.backend.data.db.PictureEntity


interface CameraRepository {
    suspend fun insertPictureWithNote(picturesEntity: PictureEntity?)
    suspend fun getNotePicture(noteId:Int):NoteWithPictures
}