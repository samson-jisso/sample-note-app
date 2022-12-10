package com.example.samnotes.presentation.camera_view.presentation

sealed class CameraEvent {
    object OnSave : CameraEvent()
    data class OnUpdate(val pictureAddress:String, val offsetX: Float, val offsetY: Float): CameraEvent()
}
