package com.example.samnotes.presentation.camera_view.backend.domain.use_cases

import com.example.samnotes.presentation.camera_view.backend.data.db.NoteWithPictures
import com.example.samnotes.presentation.camera_view.backend.domain.repository.CameraRepository

class GetNotePicture(
    private val repository: CameraRepository
) {
    suspend operator fun invoke(noteId: Int): NoteWithPictures {
        return repository.getNotePicture(noteId)
    }
}