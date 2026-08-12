package com.ppoppi.house.ui.main.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ppoppi.house.domain.model.Pet
import com.ppoppi.house.domain.repository.AuthRepository
import com.ppoppi.house.domain.repository.PetsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel
    @Inject
    constructor(
        private val petsRepository: PetsRepository,
        private val authRepository: AuthRepository,
    ) : ViewModel() {
        private val _pets = MutableStateFlow<List<Pet>>(emptyList())
        val pets: StateFlow<List<Pet>> = _pets

        private val _toastMessage = MutableSharedFlow<String>(extraBufferCapacity = 1)
        val toastMessage: SharedFlow<String> = _toastMessage

        init {
            loadPets()
        }

        fun loadPets() {
            viewModelScope.launch {
                runCatching { petsRepository.getPets() }
                    .onSuccess { _pets.value = it }
            }
        }

        fun logout() {
            viewModelScope.launch {
                authRepository
                    .authLogout()
                    .onSuccess { _toastMessage.emit("로그아웃 됐어요") }
            }
        }

        fun withdrawal() {
            viewModelScope.launch {
                authRepository
                    .authWithdrawal()
                    .onSuccess { _toastMessage.emit("탈퇴가 완료됐어요") }
            }
        }
    }
