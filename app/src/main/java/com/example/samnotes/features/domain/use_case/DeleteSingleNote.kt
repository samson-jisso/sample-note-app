package com.example.samnotes.features.domain.use_case

import com.example.samnotes.features.domain.repository.NoteRepository

class DeleteSingleNote(
   val repository: NoteRepository
) {
    suspend operator fun invoke(noteId:Int) {
      repository.deleteSingleNote(noteId)
    }
}