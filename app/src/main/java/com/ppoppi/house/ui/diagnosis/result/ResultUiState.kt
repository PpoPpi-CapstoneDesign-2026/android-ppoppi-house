package com.ppoppi.house.ui.diagnosis.result

import com.ppoppi.house.domain.model.Diagnosis

sealed interface ResultUiState {
    data object Loading : ResultUiState
    data class Success(val diagnosis: Diagnosis) : ResultUiState
    data object Error : ResultUiState
}
