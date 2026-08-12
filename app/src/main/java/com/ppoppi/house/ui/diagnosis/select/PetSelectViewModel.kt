package com.ppoppi.house.ui.diagnosis.select

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ppoppi.house.domain.model.Pet
import com.ppoppi.house.domain.repository.PetsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PetSelectViewModel
    @Inject
    constructor(
        private val petsRepository: PetsRepository,
    ) : ViewModel() {
        private val _pets = MutableStateFlow<List<Pet>>(emptyList())
        val pets: StateFlow<List<Pet>> = _pets

        fun getPets() {
            viewModelScope.launch {
                runCatching { petsRepository.getPets() }
                    .onSuccess { _pets.value = it }
            }
        }
    }
