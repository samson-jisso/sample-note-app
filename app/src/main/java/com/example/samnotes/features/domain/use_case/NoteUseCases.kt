package com.example.samnotes.features.domain.use_case

data class NoteUseCases(
    val getNotes: GetNotes,
    val deleteNote: DeleteNote,
    val deleteSingleNote: DeleteSingleNote,
    val getSingleNote: GetSingleNote,
    val insertNote: InsertNote,
    val updateNote: UpdateNote,
    val getIDFromInserted: GetIDFromInserted
)
