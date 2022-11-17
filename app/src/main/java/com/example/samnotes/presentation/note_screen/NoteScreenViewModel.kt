package com.example.samnotes.presentation.note_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.samnotes.features.domain.use_case.NoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteScreenViewModel
@Inject
constructor(
    private val useCases: NoteUseCases
) : ViewModel() {
    private var _state = MutableLiveData<NoteScreenState>()
    val state: LiveData<NoteScreenState> = _state

    init {
        viewModelScope.launch {
            val notesData = NoteScreenState(
                data = emptyList(),
                selectedNoteId = emptyList(),
            )
            _state.value = notesData
        }
    }

    fun noteScreenEvent(event: NoteScreenEvent) {
        when (event) {
            is NoteScreenEvent.SelectNote -> {
                if (!state.value?.selectedNoteId?.contains(event.id)!!) {
                    _state.value = state.value?.copy(
                        selectedNoteId = state.value!!.selectedNoteId.plus(event.id)
                    )
                }
            }
            is NoteScreenEvent.DeselectNote -> {
                if (state.value?.selectedNoteId?.contains(event.id)!!) {
                    _state.value = state.value?.copy(
                        selectedNoteId = state.value!!.selectedNoteId.minus(event.id)
                    )
                }else {
                    _state.value = state.value?.copy(
                        selectedNoteId = state.value!!.selectedNoteId.plus(event.id)
                    )
                }
            }
            is NoteScreenEvent.LongPressClicked -> {
                _state.value = state.value?.copy(
                    clicked = event.clickable
                )
            }
            is NoteScreenEvent.BackPressed -> {
                _state.value = state.value?.copy(
                    selectedNoteId = emptyList()
                )
            }

        }
    }

    fun getNotes() = viewModelScope.launch(Dispatchers.IO) {
        useCases.getNotes().collect { note ->
            this.launch(Dispatchers.Main) {
                _state.value = state.value?.copy(
                    data = note
                )
            }
        }
    }
}