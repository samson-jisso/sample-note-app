package com.example.samnotes.presentation.camera_view.backend.domain.use_cases

import com.example.samnotes.presentation.camera_view.backend.domain.repository.CameraRepository

class GetPictureId(
    val repository: CameraRepository
) {
    suspend operator fun invoke(pictureAddress:String?): Int {
        return repository.getPictureId(pictureAddress)
    }
}