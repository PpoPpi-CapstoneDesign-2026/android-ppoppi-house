package com.ppoppi.house.ui.onboarding.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ppoppi.house.domain.model.SPECIES
import com.ppoppi.house.domain.repository.PetsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PetRegisterViewModel
    @Inject
    constructor(
        private val petsRepository: PetsRepository,
    ) : ViewModel() {
        private val _breeds = MutableStateFlow<List<String>>(emptyList())
        val breeds: StateFlow<List<String>> = _breeds

        fun loadBreeds(species: SPECIES) {
            viewModelScope.launch {
                _breeds.value = emptyList()
                runCatching { petsRepository.getBreeds(species) }
                    .onSuccess { _breeds.value = it }
            }
        }
    }
