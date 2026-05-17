package com.ppoppi.house.ui.main.diary

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ppoppi.house.domain.model.Diary
import com.ppoppi.house.domain.model.HealthChecklist
import com.ppoppi.house.domain.model.MonthDiary
import com.ppoppi.house.domain.model.Pet
import com.ppoppi.house.domain.repository.DiaryRepository
import com.ppoppi.house.domain.repository.PetsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.YearMonth
import javax.inject.Inject

@HiltViewModel
class DiaryViewModel
    @Inject
    constructor(
        private val diaryRepository: DiaryRepository,
        private val petsRepository: PetsRepository,
    ) : ViewModel() {
        private val _isLoading = MutableStateFlow(true)
        val isLoading: StateFlow<Boolean> = _isLoading

        private val _monthDiaries = MutableStateFlow<List<MonthDiary>>(emptyList())
        val monthDiaries: StateFlow<List<MonthDiary>> = _monthDiaries

        private val _pets = MutableStateFlow<List<Pet>>(emptyList())
        val pets: StateFlow<List<Pet>> = _pets

        private val _dailyDiaries = MutableStateFlow<List<Diary>>(emptyList())
        val dailyDiaries: StateFlow<List<Diary>> = _dailyDiaries

        private val _isDiaryLoading = MutableStateFlow(false)
        val isDiaryLoading: StateFlow<Boolean> = _isDiaryLoading

        private val _healthChecklists = MutableStateFlow<List<HealthChecklist>>(emptyList())
        val healthChecklists: StateFlow<List<HealthChecklist>> = _healthChecklists

        init {
            viewModelScope.launch {
                val now = YearMonth.now()
                coroutineScope {
                    launch { runCatching { petsRepository.getPets() }.onSuccess { _pets.value = it } }
                    launch {
                        runCatching { diaryRepository.getDiariesMonth(now.year, now.monthValue) }
                            .onSuccess { _monthDiaries.value = it }
                    }
                    launch {
                        runCatching { diaryRepository.getHealthChecklist() }
                            .onSuccess { _healthChecklists.value = it }
                    }
                }
                _isLoading.value = false
            }
        }

        fun loadDailyDiaries(date: LocalDate) {
            viewModelScope.launch {
                _isDiaryLoading.value = true
                runCatching {
                    diaryRepository.getDiariesDaily(date.year, date.monthValue, date.dayOfMonth)
                }.onSuccess { _dailyDiaries.value = it }
                    .onFailure { Log.d("hwannow_log", "$it") }
                _isDiaryLoading.value = false
            }
        }

        fun loadMonthDiaries(yearMonth: YearMonth) {
            viewModelScope.launch {
                runCatching { diaryRepository.getDiariesMonth(yearMonth.year, yearMonth.monthValue) }
                    .onSuccess { _monthDiaries.value = it }
            }
        }

        fun addDiaryOptimistically(diary: Diary) {
            _dailyDiaries.update { it + diary }
        }

        fun deleteDiary(diaryId: Long) {
            _dailyDiaries.update { diaries -> diaries.filter { it.id != diaryId } }
            viewModelScope.launch {
                diaryRepository.deleteDiary(diaryId)
            }
        }

        fun updateDiary(
            diaryId: Long,
            memo: String,
            checkIds: List<Int>,
        ) {
            val updatedChecklist = _healthChecklists.value.filter { it.id.toInt() in checkIds }
            _dailyDiaries.update { diaries ->
                diaries.map { diary ->
                    if (diary.id == diaryId) diary.copy(memo = memo, healthChecklist = updatedChecklist) else diary
                }
            }
            viewModelScope.launch {
                diaryRepository.updateDairy(diaryId, memo, checkIds)
            }
        }
    }
