package com.example.samnotes.presentation.note_edit_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.example.samnotes.features.data.local.entity.NoteEntity
import com.example.samnotes.features.domain.use_case.NoteUseCases
import com.example.samnotes.presentation.note_screen.NoteData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteScreenViewModel
@Inject
constructor(
    private val noteUseCases: NoteUseCases,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
//    private var _state = MutableLiveData<NoteData>()
//    val state:LiveData<NoteData> = _state

    private var _state =
        mutableStateOf(NoteData(title = "please enter title", content = "please enter content"))
    val state: State<NoteData> = _state

    private var noteId: Int? = null
    private var insertTitleState: Long? = null

    //    init {
//        // retrieve from db then initialize _state
//    }
    fun handleNoteEvent(
        noteEvent: NoteEvent
    ) = viewModelScope.launch {
        when (noteEvent) {
            is NoteEvent.UpdateNoteTitle -> {
                _state.value = state.value.copy(
                    title = noteEvent.titleEntered
                )
                if (insertTitleState == null) {
                    insertNote(
                        id = noteId,
                        title = state.value.title,
                        content = state.value.content
                    )
                } else {
                    noteUseCases.updateNote(
                        NoteEntity(
                            id = insertTitleState!!.toInt(),
                            title = state.value.title,
                            content = state.value.content
                        )
                    )
                }


            }
            is NoteEvent.UpdateNoteContent -> {
                _state.value = state.value.copy(
                    content = noteEvent.contentEntered
                )
                if (insertTitleState == null) {
                    val rowId = noteUseCases.insertNote(
                        NoteEntity(
                            id = noteId,
                            title = state.value.title,
                            content = state.value.content,

                            )
                    )
                    insertTitleState = rowId

                }else {
                    noteUseCases.updateNote(
                        NoteEntity(
                            id = insertTitleState!!.toInt(),
                            title = state.value.title,
                            content = state.value.content
                        )
                    )
                }
            }
        }
    }

    private fun insertNote(id: Int?, title: String, content: String) = viewModelScope.launch {
        val rowId = noteUseCases.insertNote(
            NoteEntity(
                id = id,
                title = title,
                content = content
            )
        )
        insertTitleState = rowId
    }
}