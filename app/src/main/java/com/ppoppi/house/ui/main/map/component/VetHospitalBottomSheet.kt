package com.ppoppi.house.ui.main.map.component

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.ppoppi.house.domain.model.HospitalInfo
import com.ppoppi.house.ui.theme.Black
import com.ppoppi.house.ui.theme.Gray200
import com.ppoppi.house.ui.theme.Gray300
import com.ppoppi.house.ui.theme.Gray400
import com.ppoppi.house.ui.theme.PpoPpiTheme
import com.ppoppi.house.ui.theme.Primary400
import com.ppoppi.house.ui.theme.White

@Composable
fun VetHospitalBottomSheet(hospital: HospitalInfo) {
    val context = LocalContext.current

    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp),
    ) {
        Text(
            text = hospital.name,
            style = PpoPpiTheme.typography.headline1,
            color = Gray400,
            modifier = Modifier.padding(horizontal = 6.dp),
        )

        Row(
            modifier = Modifier.padding(top = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            BusinessStatusLabel(
                businessStatus = hospital.operationLabel,
            )

            Text(
                text = "${hospital.distanceMeter}m",
                style = PpoPpiTheme.typography.label1,
                color = Gray300,
            )
        }

        HorizontalDivider(
            modifier = Modifier.padding(top = 16.dp),
            thickness = 1.dp,
            color = Gray200,
        )

        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
                    .padding(top = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Text(
                text = "📍",
                style = PpoPpiTheme.typography.headline1,
            )

            Text(
                text = hospital.address,
                style = PpoPpiTheme.typography.body1,
                color = Black,
            )
        }
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
                    .padding(top = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Text(
                text = "📞",
                style = PpoPpiTheme.typography.headline1,
            )

            Text(
                text = hospital.callNumber,
                style = PpoPpiTheme.typography.body1,
                color = Black,
            )
        }
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
                    .padding(top = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Text(
                text = "⏰",
                style = PpoPpiTheme.typography.headline1,
            )

            Text(
                text = hospital.businessHours,
                style = PpoPpiTheme.typography.body1,
                color = Black,
            )
        }

        Button(
            onClick = {
                val intent = Intent(Intent.ACTION_DIAL, "tel:${hospital.callNumber}".toUri())
                context.startActivity(intent)
            },
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 6.dp)
                    .padding(bottom = 20.dp, top = 44.dp),
            shape = RoundedCornerShape(8.dp),
            colors =
                ButtonDefaults.buttonColors(
                    containerColor = Primary400,
                ),
            contentPadding = PaddingValues(vertical = 12.dp),
        ) {
            Text(
                text = "바로 전화하기",
                style = PpoPpiTheme.typography.title1,
                color = White,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun VetHospitalBottomSheetPreview() {
    PpoPpiTheme {
        VetHospitalBottomSheet(
            HospitalInfo(
                id = 1,
                name = "test",
                address = "test",
                callNumber = "test",
                businessHours = "test",
                operationLabel = "test",
                distanceMeter = 1,
                is24Hour = true
            ),
        )
    }
}
