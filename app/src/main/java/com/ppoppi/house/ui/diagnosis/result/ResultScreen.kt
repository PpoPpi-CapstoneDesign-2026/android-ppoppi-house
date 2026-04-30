package com.ppoppi.house.ui.diagnosis.result

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ppoppi.house.R
import com.ppoppi.house.domain.Diagnosis
import com.ppoppi.house.domain.Triage
import com.ppoppi.house.domain.Triage.Companion.toColor
import com.ppoppi.house.ui.diagnosis.result.component.ConfidenceScore
import com.ppoppi.house.ui.diagnosis.result.component.ResultCard
import com.ppoppi.house.ui.diagnosis.result.component.TriageBadge
import com.ppoppi.house.ui.theme.Black
import com.ppoppi.house.ui.theme.Gray10
import com.ppoppi.house.ui.theme.Gray100
import com.ppoppi.house.ui.theme.Gray200
import com.ppoppi.house.ui.theme.Gray300
import com.ppoppi.house.ui.theme.PpoPpiTheme
import com.ppoppi.house.ui.theme.White
import com.ppoppi.house.ui.util.noRippleClickable

@Composable
fun ResultScreen(
    navigateToHome: () -> Unit,
    navigateToMap: () -> Unit,
    navigateToDiagnosis: () -> Unit,
    imageUri: Uri,
    diagnosis: Diagnosis,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = { ResultTopAppBar(navigateToHome) },
        containerColor = White,
        modifier = modifier,
    ) { innerPadding ->
        Column(
            modifier =
                Modifier
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            AsyncImage(
                model = imageUri,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier =
                    Modifier
                        .padding(top = 20.dp)
                        .padding(horizontal = 22.dp)
                        .size(330.dp)
                        .clip(RoundedCornerShape(12.dp)),
            )

            TriageBadge(
                triage = diagnosis.triageKey,
                modifier = Modifier
                    .padding(top = 14.dp)
            )

            Text(
                text = stringResource(
                    R.string.diagnosis_result_diagnosis_title,
                    diagnosis.guideTitle,
                    diagnosis.affectedArea
                ),
                style = PpoPpiTheme.typography.title1,
                color = Black,
                modifier = Modifier.padding(top = 12.dp)
            )

            ConfidenceScore(
                confidenceScore = diagnosis.triageConfidence,
                triageColor = diagnosis.triageKey.toColor()[1],
                modifier = Modifier
                    .padding(top = 12.dp)
                    .padding(horizontal = 34.dp)
            )

            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .padding(top = 8.dp),
                thickness = 1.dp,
                color = Gray100,
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Gray10)
                    .padding(horizontal = 30.dp)
            ) {
                ResultCard(
                    diagnosis = diagnosis,
                    modifier = Modifier.padding(top = 28.dp),
                )

                Text(
                    text = stringResource(R.string.diagnosis_result_diagnosis_card_description),
                    style = PpoPpiTheme.typography.body5,
                    color = Gray200,
                    modifier = Modifier.padding(top = 12.dp)
                )

                Button(
                    onClick = {
                        if (diagnosis.triageKey == Triage.URGENT || diagnosis.triageKey == Triage.SOON)
                            navigateToMap()
                        else
                            navigateToHome()

                    },
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(top = 60.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors =
                        ButtonDefaults.buttonColors(
                            containerColor = diagnosis.triageKey.toColor()[1],
                        ),
                    contentPadding = PaddingValues(vertical = 12.dp),
                ) {
                    Text(
                        text = if (diagnosis.triageKey == Triage.URGENT || diagnosis.triageKey == Triage.SOON) stringResource(
                            R.string.diagnosis_result_bottom_bar_button_to_map
                        ) else stringResource(
                            R.string.diagnosis_result_bottom_bar_button_to_home
                        ),
                        style = PpoPpiTheme.typography.title1,
                        color = White,
                    )
                }

                Text(
                    text = stringResource(R.string.diagnosis_result_rediagnosis),
                    style = PpoPpiTheme.typography.body5,
                    color = Gray300,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 12.dp, bottom = 20.dp)
                        .noRippleClickable(navigateToDiagnosis)
                )
            }
        }
    }
}

