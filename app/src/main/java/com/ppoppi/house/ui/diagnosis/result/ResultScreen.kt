package com.ppoppi.house.ui.diagnosis.result

import android.net.Uri
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ppoppi.house.ui.diagnosis.result.component.LoadingContent
import com.ppoppi.house.ui.diagnosis.result.component.ResultContent
import com.ppoppi.house.ui.theme.White

@Composable
fun ResultScreen(
    petName: String,
    uiState: ResultUiState,
    imageUri: Uri,
    navigateToHome: () -> Unit,
    navigateToMap: () -> Unit,
    navigateToDiagnosis: () -> Unit,
    modifier: Modifier = Modifier,
) {

    Scaffold(
        topBar = { ResultTopAppBar(navigateToHome) },
        containerColor = White,
        modifier = modifier,
    ) { innerPadding ->
        val modifier = Modifier.padding(innerPadding)
        when (uiState) {
            ResultUiState.Loading ->
                LoadingContent(
                    name = petName,
                    modifier = modifier,
                )
            ResultUiState.Error -> Unit
            is ResultUiState.Success ->
                ResultContent(
                    diagnosis = uiState.diagnosis,
                    imageUri = imageUri,
                    navigateToHome = navigateToHome,
                    navigateToMap = navigateToMap,
                    navigateToDiagnosis = navigateToDiagnosis,
                    modifier = modifier,
                )
        }
    }
}


