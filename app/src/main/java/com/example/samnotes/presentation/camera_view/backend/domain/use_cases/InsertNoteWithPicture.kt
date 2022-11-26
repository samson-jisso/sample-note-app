package com.example.samnotes.presentation.camera_view.backend.domain.use_cases

import com.example.samnotes.presentation.camera_view.backend.data.db.PicturesEntity
import com.example.samnotes.presentation.camera_view.backend.domain.repository.CameraRepository

class InsertNoteWithPicture(
 private val repository: CameraRepository
 )
{
   suspend operator fun invoke(notePicturesEntity: PicturesEntity) {
        repository.insertPictureWithNote(notePicturesEntity)
    }
}