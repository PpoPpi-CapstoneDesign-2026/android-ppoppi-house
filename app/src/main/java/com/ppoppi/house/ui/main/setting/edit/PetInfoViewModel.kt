package com.ppoppi.house.ui.main.setting.edit

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
class PetInfoViewModel
    @Inject
    constructor(
        private val petsRepository: PetsRepository,
    ) : ViewModel() {
        private val _breeds = MutableStateFlow<List<String>>(emptyList())
        val breeds: StateFlow<List<String>> = _breeds

        private val _isRegistered = MutableSharedFlow<Unit>(extraBufferCapacity = 1)
        val isRegistered: SharedFlow<Unit> = _isRegistered

        private val _isDeleted = MutableSharedFlow<Unit>(extraBufferCapacity = 1)
        val isDeleted: SharedFlow<Unit> = _isDeleted

        fun loadBreeds(species: SPECIES) {
            viewModelScope.launch {
                _breeds.value = emptyList()
                runCatching { petsRepository.getBreeds(species) }
                    .onSuccess { _breeds.value = it }
            }
        }

        fun registerPet(pet: Pet) {
            viewModelScope.launch {
                petsRepository
                    .postPet(pet)
                    .onSuccess { _isRegistered.emit(Unit) }
            }
        }

        fun putPet(
            petId: Long,
            pet: Pet,
        ) {
            viewModelScope.launch {
                petsRepository
                    .putPet(petId, pet)
                    .onSuccess { _isRegistered.emit(Unit) }
            }
        }

        fun deletePet(petId: Long) {
            viewModelScope.launch {
                petsRepository
                    .deletePet(petId)
                    .onSuccess { _isDeleted.emit(Unit) }
            }
        }
    }
