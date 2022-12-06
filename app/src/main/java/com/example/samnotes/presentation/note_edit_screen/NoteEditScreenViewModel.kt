package com.example.samnotes.presentation.note_edit_screen

import android.util.Log
import androidx.core.net.toUri
import androidx.lifecycle.*
import com.example.samnotes.features.domain.model.Note
import com.example.samnotes.features.domain.use_case.NoteUseCases
import com.example.samnotes.presentation.camera_view.backend.domain.use_cases.CameraUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class NoteEditScreenViewModel
@Inject
constructor(
    private val noteUseCases: NoteUseCases,
    private val cameraUseCases: CameraUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private var _state = MutableLiveData<NoteEditScreenState>()
    val state: LiveData<NoteEditScreenState> = _state
    var noteId: Int? = savedStateHandle.get<Int>("noteId")
    private val enterTitle = "Enter Title"
    private val enterContent = "Enter Content"

    init {
        if (noteId == -1) {
            // you create a n note id
            val noteState = NoteEditScreenState(0, enterTitle, enterContent)
            _state.value = noteState
        } else {

            //get from db
            viewModelScope.launch(Dispatchers.IO) {
                val noteImage = cameraUseCases.getNotePicture(noteId!!)
                if (noteImage.pictures.isNotEmpty()) {
                    this.launch(Dispatchers.Main) {
                        noteId = noteImage.note.noteId
                        _state.value = NoteEditScreenState(
                            id = noteImage.note.noteId,
                            title = noteImage.note.title,
                            content = noteImage.note.content,
                            photoUri = noteImage.pictures.first().pictureAddress.toUri()
                        )
                    }
                } else {
                    val noteData = noteUseCases.getSingleNote(noteId!!)
                    this.launch(Dispatchers.Main) {
                        noteId = noteData?.noteId
                        _state.value = NoteEditScreenState(
                            id = noteData?.noteId,
                            title = noteData?.title,
                            content = noteData?.content,
                        )
                    }
                }
            }
        }
    }

    fun handleNoteEvent(
        noteEvent: NoteEvent
    ) = viewModelScope.launch {
        when (noteEvent) {
            is NoteEvent.ClearTitleHint -> {

                if (noteId == -1 || state.value?.title == enterTitle) {
                    _state.value = state.value?.copy(
                        title = ""
                    )
                }
            }
            is NoteEvent.ClearContentHint -> {
                if (noteId == -1 || state.value?.content == enterContent) {
                    _state.value = state.value?.copy(
                        content = ""
                    )
                }
            }
            is NoteEvent.TitleFocusChanged -> {
                if (state.value?.title == "") {
                    _state.value = state.value?.copy(
                        title = enterTitle
                    )
                }
            }
            is NoteEvent.ContentFocusChanged -> {
                if (state.value?.content == "") {
                    _state.value = state.value?.copy(
                        content = enterContent
                    )
                }
            }
            is NoteEvent.UpdateNoteTitle -> {
                _state.value = state.value?.copy(
                    title = noteEvent.titleEntered
                )
                checkUpdate()
            }
            is NoteEvent.UpdateNoteContent -> {
                _state.value = state.value?.copy(
                    content = noteEvent.contentEntered
                )
                checkUpdate()
            }

        }

    }

    private suspend fun checkUpdate() {
        if (
            noteId == -1
            && state.value!!.title!!.isNotBlank()
            && state.value!!.content!!.isNotBlank()
        ) {
            val randomId = randomIdNum()
            insertNote(randomId, title = state.value?.title, content = state.value?.content)
            noteId = randomId
        } else {
            noteUseCases.updateNote(
                Note(
                    noteId = noteId,
                    title = state.value?.title,
                    content = state.value?.content
                )
            )
        }
    }

    private fun insertNote(id: Int?, title: String?, content: String?) = viewModelScope.launch {
        noteUseCases.insertNote(
            Note(
                noteId = id,
                title = title,
                content = content
            )
        )
    }

    private fun randomIdNum(): Int? {
        return try {
            Random.nextInt(100000, 9999999)
        } catch (e: Exception) {
            Log.e("randomIdNum", "randomIdError: $e", e)
            null
        }
    }
}