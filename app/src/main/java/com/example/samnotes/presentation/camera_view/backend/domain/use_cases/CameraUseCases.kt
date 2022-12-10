package com.example.samnotes.presentation.camera_view.backend.domain.use_cases

data class CameraUseCases(
    val insertPicture: InsertPicture,
    val getNotePicture: GetNotePicture,
    val updateNotePicture: UpdateNotePicture,
    val getPictureId: GetPictureId,
)