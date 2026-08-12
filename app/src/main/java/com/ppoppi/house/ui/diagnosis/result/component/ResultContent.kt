package com.ppoppi.house.ui.diagnosis.result.component

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
import com.ppoppi.house.domain.model.Diagnosis
import com.ppoppi.house.domain.model.Triage
import com.ppoppi.house.domain.model.Triage.Companion.toColor
import com.ppoppi.house.ui.theme.Black
import com.ppoppi.house.ui.theme.Gray10
import com.ppoppi.house.ui.theme.Gray100
import com.ppoppi.house.ui.theme.Gray200
import com.ppoppi.house.ui.theme.Gray300
import com.ppoppi.house.ui.theme.PpoPpiTheme
import com.ppoppi.house.ui.theme.White
import com.ppoppi.house.ui.util.noRippleClickable

@Composable
fun ResultContent(
    diagnosis: Diagnosis,
    imageUri: Uri,
    navigateToHome: () -> Unit,
    navigateToMap: () -> Unit,
    navigateToDiagnosis: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState()),
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
            triage = diagnosis.triage,
            modifier =
                Modifier
                    .padding(top = 14.dp),
        )

        Text(
            text =
                if (diagnosis.affectedArea.isEmpty()) {
                    "사진 분석 결과 | ${diagnosis.diseaseName}"
                } else {
                    "사진 분석 결과 | ${diagnosis.diseaseName} | ${diagnosis.affectedArea}"
                },
            style = PpoPpiTheme.typography.title1,
            color = Black,
            modifier = Modifier.padding(top = 12.dp),
        )

        ConfidenceScore(
            confidenceScore = diagnosis.triageConfidence,
            triageColor = diagnosis.triage.toColor()[1],
            modifier =
                Modifier
                    .padding(top = 12.dp)
                    .padding(horizontal = 34.dp),
        )

        HorizontalDivider(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .padding(top = 8.dp),
            thickness = 1.dp,
            color = Gray100,
        )

        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .background(Gray10)
                    .padding(horizontal = 30.dp),
        ) {
            ResultCard(
                diagnosis = diagnosis,
                modifier = Modifier.padding(top = 28.dp),
            )

            Text(
                text = stringResource(R.string.diagnosis_result_diagnosis_card_description),
                style = PpoPpiTheme.typography.body5,
                color = Gray200,
                modifier = Modifier.padding(top = 12.dp),
            )

            Button(
                onClick = {
                    if (diagnosis.triage == Triage.URGENT || diagnosis.triage == Triage.SOON) {
                        navigateToMap()
                    } else {
                        navigateToHome()
                    }
                },
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 60.dp),
                shape = RoundedCornerShape(8.dp),
                colors =
                    ButtonDefaults.buttonColors(
                        containerColor = diagnosis.triage.toColor()[1],
                    ),
                contentPadding = PaddingValues(vertical = 12.dp),
            ) {
                Text(
                    text =
                        if (diagnosis.triage == Triage.URGENT || diagnosis.triage == Triage.SOON) {
                            stringResource(
                                R.string.diagnosis_result_bottom_bar_button_to_map,
                            )
                        } else {
                            stringResource(
                                R.string.diagnosis_result_bottom_bar_button_to_home,
                            )
                        },
                    style = PpoPpiTheme.typography.title1,
                    color = White,
                )
            }

            Text(
                text = stringResource(R.string.diagnosis_result_rediagnosis),
                style = PpoPpiTheme.typography.body5,
                color = Gray300,
                modifier =
                    Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 12.dp, bottom = 20.dp)
                        .noRippleClickable(navigateToDiagnosis),
            )
        }
    }
}
