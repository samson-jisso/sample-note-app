package com.example.samnotes.presentation.note_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.samnotes.features.domain.use_case.NoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onEach
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
            data = emptyList()
        )
        _state.value = notesData
     }
    }

    fun getNotes() = viewModelScope.launch {
        useCases.getNotes().collect { notes ->
            _state.value = state.value?.copy(
                data = notes
            )
        }
    }
}