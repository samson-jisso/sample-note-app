package com.example.samnotes.features.domain.use_case

data class NoteUseCases(
    val getNotes: GetNotes,
    val deleteNote: DeleteNote,
    val getSingleNote: GetSingleNote,
    val insertNote: InsertNote
)