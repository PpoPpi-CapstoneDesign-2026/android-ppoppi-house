package com.ppoppi.house.ui.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ppoppi.house.domain.model.Disease
import com.ppoppi.house.domain.repository.DiseaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
    @Inject
    constructor(
        private val diseaseRepository: DiseaseRepository,
    ) : ViewModel() {
        private val _diseases = MutableStateFlow<List<Disease>?>(null)
        val diseases: StateFlow<List<Disease>?> = _diseases

        private var searchJob: Job? = null

        init {
            viewModelScope.launch {
                runCatching { diseaseRepository.getGeneticDiseaseRandom() }
                    .onSuccess { _diseases.value = it.take(MAX_DISEASE_COUNT) }
            }
        }

        fun loadDiseases(keyword: String) {
            searchJob?.cancel()
            searchJob =
                viewModelScope.launch {
                    delay(SEARCH_DEBOUNCE_MS)
                    runCatching { diseaseRepository.getGeneticDiseaseSearch(keyword) }
                        .onSuccess {
                            _diseases.value = it.take(MAX_DISEASE_COUNT)
                        }
                }
        }

        companion object {
            private const val MAX_DISEASE_COUNT = 3
            private const val SEARCH_DEBOUNCE_MS = 300L
        }
    }
