package com.example.samnotes.presentation.note_edit_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.example.samnotes.features.data.local.entity.NoteEntity
import com.example.samnotes.features.domain.use_case.NoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class NoteEditScreenViewModel
@Inject
constructor(
    private val noteUseCases: NoteUseCases,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private var _state = MutableLiveData<NoteEditScreenState>()
    val state: LiveData<NoteEditScreenState> = _state

    private var noteId: Int? = savedStateHandle.get<Int>("noteId")

    private var insertTitleState: Long? = null

    init {
        if (noteId == -1) {
            // you create a n note id
            val noteState = NoteEditScreenState(0, "Enter Title", "Enter content")
            _state.value = noteState
        } else {
            //get from db
            viewModelScope.launch {
                noteUseCases.getSingleNote(noteId!!).collect {
                    _state.value = NoteEditScreenState(
                        id = it.id ?: 0,
                        title = it.title ?: "",
                        content = it.content ?: ""
                    )
                }
            }
        }
    }

    fun handleNoteEvent(
        noteEvent: NoteEvent
    ) = viewModelScope.launch {
        when (noteEvent) {
            is NoteEvent.UpdateNoteTitle -> {
                _state.value = state.value?.copy(
                    title = noteEvent.titleEntered
                )
                if (
                    noteId == -1
                    && state.value!!.title.isNotBlank()
                    && state.value!!.content.isNotBlank()
                ) {
                    val randomId = randomIdNum()
                    insertNote(randomId, title = state.value?.title, content = state.value?.content)
                    noteId = randomId
                } else {
                    noteUseCases.updateNote(
                        NoteEntity(
                            id = noteId,
                            title = state.value?.title,
                            content = state.value?.content
                        )
                    )
                }
            }
            is NoteEvent.UpdateNoteContent -> {
                _state.value = state.value?.copy(
                    content = noteEvent.contentEntered
                )
                if (
                    noteId == -1
                    && state.value!!.title.isNotBlank()
                    && state.value!!.content.isNotBlank()
                ) {
                    val randomId = randomIdNum()
                    insertNote(randomId, title = state.value?.title, content = state.value?.content)
                    noteId = randomId
                } else {
                    noteUseCases.updateNote(
                        NoteEntity(
                            id = noteId,
                            title = state.value?.title,
                            content = state.value?.content
                        )
                    )
                }
            }

        }

    }

    private fun insertNote(id: Int?, title: String?, content: String?) = viewModelScope.launch {
        val rowId = noteUseCases.insertNote(
            NoteEntity(
                id = id,
                title = title,
                content = content
            )
        )
        insertTitleState = rowId
    }

    private fun randomIdNum(): Int {
        return Random.nextInt(100000, 999999)
    }
}