package com.ppoppi.house.ui.main.map.component

import android.content.Intent
import android.net.Uri
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
import com.google.android.gms.maps.model.LatLng
import com.ppoppi.house.domain.BusinessStatus
import com.ppoppi.house.domain.VetHospital
import com.ppoppi.house.ui.theme.Black
import com.ppoppi.house.ui.theme.Gray200
import com.ppoppi.house.ui.theme.Gray300
import com.ppoppi.house.ui.theme.Gray400
import com.ppoppi.house.ui.theme.PpoPpiTheme
import com.ppoppi.house.ui.theme.Primary400
import com.ppoppi.house.ui.theme.White

@Composable
fun VetHospitalBottomSheet(hospital: VetHospital) {
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
                businessStatus = hospital.businessStatus,
            )

            Text(
                text = "${hospital.distance}m",
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
                text = hospital.phone,
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
                text = hospital.businessTime,
                style = PpoPpiTheme.typography.body1,
                color = Black,
            )
        }

        Button(
            onClick = {
                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${hospital.phone}"))
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
            VetHospital(
                name = "행복 동물병원",
                location = LatLng(37.55075076074825, 127.0754535962874),
                phone = "010-1234-1234",
                businessStatus = BusinessStatus.OPEN,
                distance = 222,
                address = "서울특별시 어쩌구 저쩌구 주소",
                businessTime = "평일 9:00 - 16:00",
            ),
        )
    }
}
