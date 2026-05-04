package com.ppoppi.house.ui.onboarding.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ppoppi.house.domain.model.Pet
import com.ppoppi.house.domain.model.SPECIES
import com.ppoppi.house.domain.repository.PetsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
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

        private val _isRegistered = MutableSharedFlow<Unit>(extraBufferCapacity = 1)
        val isRegistered: SharedFlow<Unit> = _isRegistered

        fun loadBreeds(species: SPECIES) {
            viewModelScope.launch {
                _breeds.value = emptyList()
                runCatching { petsRepository.getBreeds(species) }
                    .onSuccess { _breeds.value = it }
            }
        }

        fun registerPet(pet: Pet) {
            viewModelScope.launch {
                petsRepository.postPet(pet)
                    .onSuccess { _isRegistered.emit(Unit) }
            }
        }
    }
