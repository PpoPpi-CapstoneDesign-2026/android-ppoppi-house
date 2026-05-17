package com.ppoppi.house.ui.main.diary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ppoppi.house.domain.model.HealthChecklist
import com.ppoppi.house.domain.repository.DiaryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiaryAddViewModel
    @Inject
    constructor(
        private val diaryRepository: DiaryRepository,
    ) : ViewModel() {
        private val _healthChecklists = MutableStateFlow<List<HealthChecklist>>(emptyList())
        val healthChecklists: StateFlow<List<HealthChecklist>> = _healthChecklists

        private val _savedEvent = MutableSharedFlow<Unit>()
        val savedEvent: SharedFlow<Unit> = _savedEvent

        init {
            viewModelScope.launch {
                runCatching { diaryRepository.getHealthChecklist() }
                    .onSuccess { _healthChecklists.value = it }
            }
        }

        fun postDiary(
            petId: Long,
            memo: String,
            checkIds: List<Long>,
        ) {
            viewModelScope.launch {
                diaryRepository
                    .postDiary(petId, memo, checkIds)
                    .onSuccess { _savedEvent.emit(Unit) }
            }
        }
    }
