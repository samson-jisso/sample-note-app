package com.example.samnotes.presentation.camera_view.backend.domain.use_cases

import com.example.samnotes.presentation.camera_view.backend.data.db.PictureEntity
import com.example.samnotes.presentation.camera_view.backend.domain.repository.CameraRepository

class UpdateNotePicture(
    val repository: CameraRepository
) {
    suspend operator fun invoke(pictureEntity: PictureEntity?) {
        repository.updateNotePicture(pictureEntity)
    }
}