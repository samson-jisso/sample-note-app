package com.example.samnotes.features.domain.use_case

import com.example.samnotes.features.domain.repository.NoteRepository

class GetIDFromInserted(
    private val repository: NoteRepository
) {
   suspend operator fun invoke(rowId:Long):Int {
       return repository.getIdFromRowId(rowId)
    }
}