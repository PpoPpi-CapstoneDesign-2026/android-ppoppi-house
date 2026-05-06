package com.ppoppi.house.ui.main.diary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ppoppi.house.domain.model.MonthDiary
import com.ppoppi.house.domain.repository.DiaryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.YearMonth
import javax.inject.Inject

@HiltViewModel
class DiaryViewModel
    @Inject
    constructor(
        private val diaryRepository: DiaryRepository,
    ) : ViewModel() {
        private val _monthDiaries = MutableStateFlow<List<MonthDiary>>(emptyList())
        val monthDiaries: StateFlow<List<MonthDiary>> = _monthDiaries

        init {
            loadMonthDiaries(YearMonth.now())
        }

        fun loadMonthDiaries(yearMonth: YearMonth) {
            viewModelScope.launch {
                runCatching { diaryRepository.getDiariesMonth(yearMonth.year, yearMonth.monthValue) }
                    .onSuccess { _monthDiaries.value = it }
            }
        }
    }
