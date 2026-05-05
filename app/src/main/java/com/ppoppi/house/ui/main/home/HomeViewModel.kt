package com.ppoppi.house.ui.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ppoppi.house.domain.model.Diagnosis
import com.ppoppi.house.domain.model.Disease
import com.ppoppi.house.domain.model.Pet
import com.ppoppi.house.domain.repository.DiagnosisRepository
import com.ppoppi.house.domain.repository.DiseaseRepository
import com.ppoppi.house.domain.repository.PetsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
    @Inject
    constructor(
        private val diseaseRepository: DiseaseRepository,
        private val diagnosisRepository: DiagnosisRepository,
        private val petsRepository: PetsRepository,
    ) : ViewModel() {
        private val _diseases = MutableStateFlow<List<Disease>?>(null)
        val diseases: StateFlow<List<Disease>?> = _diseases

        private val _todayDiagnosis = MutableStateFlow<Diagnosis?>(null)
        val todayDiagnosis: StateFlow<Diagnosis?> = _todayDiagnosis

        private val _pets = MutableStateFlow<List<Pet>>(emptyList())
        val pets: StateFlow<List<Pet>> = _pets

        private var searchJob: Job? = null

        init {
            viewModelScope.launch {
                runCatching { petsRepository.getPets() }
                    .onSuccess { _pets.value = it }
            }
            viewModelScope.launch {
                runCatching { diseaseRepository.getGeneticDiseaseRandom() }
                    .onSuccess { _diseases.value = it.take(MAX_DISEASE_COUNT) }
            }
            loadDiagnosis(_pets.value.firstOrNull()?.id ?: 0)
        }

        fun loadDiagnosis(petId: Long) {
            viewModelScope.launch {
                runCatching {
                    diagnosisRepository.getDiagnosisToday(
                        petId = petId,
                        date = LocalDate.now(),
                    )
                }.onSuccess { diagnosis ->
                    if (diagnosis.hasDiagnosis) {
                        _todayDiagnosis.value = diagnosis
                    } else {
                        _todayDiagnosis.value = null
                    }
                }.onFailure {
                    _todayDiagnosis.value = null
                }
            }
        }

        fun loadDiseases(keyword: String) {
            searchJob?.cancel()
            searchJob =
                viewModelScope.launch {
                    delay(SEARCH_DEBOUNCE_MS)
                    if (keyword.isEmpty()) {
                        runCatching { diseaseRepository.getGeneticDiseaseRandom() }
                            .onSuccess { _diseases.value = it.take(MAX_DISEASE_COUNT) }
                    } else {
                        runCatching { diseaseRepository.getGeneticDiseaseSearch(keyword) }
                            .onSuccess { _diseases.value = it.take(MAX_DISEASE_COUNT) }
                    }
                }
        }

        companion object {
            private const val MAX_DISEASE_COUNT = 3
            private const val SEARCH_DEBOUNCE_MS = 300L
        }
    }
