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
                data = arrayListOf(),
                selectedNoteId = arrayListOf(),
            )
            _state.value = notesData
        }
    }

    fun noteScreenEvent(event: NoteScreenEvent) {
        when (event) {
            is NoteScreenEvent.SelectNote -> {
                if (!state.value?.selectedNoteId?.contains(event.id)!!) {
//                    _state.value!!.selectedNoteId.add(event.id!!)
                    _state.value = state.value?.copy(
                        selectedNoteId = _state.value!!.selectedNoteId.plus(event.id!!)
                    )
                }
            }
            is NoteScreenEvent.SelectOrDeselectNote -> {
                if (state.value?.selectedNoteId?.contains(event.id)!!) {
//                    _state.value!!.selectedNoteId.remove(event.id!!)

                    println("hello")
                    println("stateValueFirst:${state.value!!.selectedNoteId}")
//                    _state.value  = state.value!!.copy(
//                        selectedNoteId = _state.value!!.selectedNoteId.
//                    )
                    _state.value = state.value?.copy(
                        selectedNoteId = _state.value!!.selectedNoteId.minus(event.id!!)
                    )
                    println(state.value?.selectedNoteId)
                } else {
                    _state.value = state.value?.copy(
                        selectedNoteId = _state.value!!.selectedNoteId.plus(event.id!!)
                    )
                    println(state.value?.selectedNoteId)
                }
            }
            is NoteScreenEvent.LongPressClicked -> {
                _state.value = state.value?.copy(
                    clicked = event.clickable
                )
            }
            is NoteScreenEvent.BackPressed -> {
                _state.value = state.value?.copy(
                    selectedNoteId = arrayListOf()
                )
            }
            is NoteScreenEvent.DeleteSelectedNotes -> {
                println("delete ${state.value?.selectedNoteId}")
                _state.value?.selectedNoteId?.forEach { selectedNoteId ->
                    viewModelScope.launch(Dispatchers.IO) {
                        useCases.deleteSingleNote(selectedNoteId)
                        getNotes()
                    }
//                    _state.value!!.selectedNoteId.remove(selectedNoteId)
                    _state.value = state.value?.copy(
                        selectedNoteId = state.value?.selectedNoteId!!.minus(selectedNoteId)
                    )

                }
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