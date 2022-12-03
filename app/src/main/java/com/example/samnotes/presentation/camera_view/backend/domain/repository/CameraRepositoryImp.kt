package com.example.samnotes.presentation.camera_view.backend.domain.repository

import com.example.samnotes.presentation.camera_view.backend.data.db.NoteWithPictures
import com.example.samnotes.presentation.camera_view.backend.data.db.PictureDao
import com.example.samnotes.presentation.camera_view.backend.data.db.PictureEntity

class CameraRepositoryImp(
    private val pictureDao: PictureDao
) : CameraRepository {
    override suspend fun insertPictureWithNote(picturesEntity: PictureEntity?) {
        pictureDao.insertPicture(picturesEntity)
    }

    override suspend fun getNotePicture(noteId: Int): NoteWithPictures {
        return pictureDao.getNotePicture(noteId)
    }

}