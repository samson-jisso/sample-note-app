package com.example.samnotes.features_notes.domain.use_case

data class NoteUseCases(
    val addNote: AddNote,
    val deleteNote: DeleteNote,
    val getNotes: GetNotes,
    val getNoteById: GetNoteById
)
