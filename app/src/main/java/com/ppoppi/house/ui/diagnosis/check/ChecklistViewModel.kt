package com.ppoppi.house.ui.diagnosis.check

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ppoppi.house.domain.repository.SymptomRepository
import com.ppoppi.house.ui.diagnosis.check.model.SymptomItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChecklistViewModel
    @Inject
    constructor(
        private val symptomRepository: SymptomRepository,
    ) : ViewModel() {
        private val _symptoms = MutableStateFlow<List<SymptomItem>>(emptyList())
        val symptoms: StateFlow<List<SymptomItem>> = _symptoms

        init {
            loadSymptoms()
        }

        private fun loadSymptoms() {
            viewModelScope.launch {
                runCatching { symptomRepository.getSymptoms() }
                    .onSuccess { result ->
                        _symptoms.value = result.map { SymptomItem(it.id, it.description, false) }
                    }
            }
        }
    }
