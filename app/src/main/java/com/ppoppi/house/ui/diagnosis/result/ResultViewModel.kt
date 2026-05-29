package com.ppoppi.house.ui.diagnosis.result

import android.app.Application
import android.net.Uri
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.ppoppi.house.domain.repository.DiagnosisRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject

@HiltViewModel
class ResultViewModel
    @Inject
    constructor(
        application: Application,
        private val diagnosisRepository: DiagnosisRepository,
    ) : AndroidViewModel(application) {
        private val _uiState = MutableStateFlow<ResultUiState>(ResultUiState.Loading)
        val uiState: StateFlow<ResultUiState> = _uiState

        fun diagnose(
            petId: Long,
            symptomIds: List<Int>,
            imageUri: Uri,
        ) {
            viewModelScope.launch(Dispatchers.IO) {
                val bytes =
                    getApplication<Application>()
                        .contentResolver
                        .openInputStream(imageUri)
                        ?.use { it.readBytes() }
                if (bytes == null) {
                    _uiState.value = ResultUiState.Error
                    return@launch
                }
                val imagePart =
                    bytes
                        .toRequestBody("image/*".toMediaType())
                        .let { MultipartBody.Part.createFormData("image", "image.jpg", it) }
                runCatching { diagnosisRepository.postDiagnosis(petId, symptomIds, imagePart) }
                    .onSuccess {
                        if (it.triageConfidence < 70) {
                            _uiState.value = ResultUiState.Error
                        } else {
                            _uiState.value = ResultUiState.Success(it)
                        }
                    }
                    .onFailure {
                        Log.d("hwannow_log", "why failed?")
                        Log.d("hwannow_log", "$it")
                        _uiState.value = ResultUiState.Error }
            }
        }
    }
