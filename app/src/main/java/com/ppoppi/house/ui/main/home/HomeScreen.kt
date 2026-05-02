package com.ppoppi.house.ui.main.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ppoppi.house.R
import com.ppoppi.house.domain.model.COLOR
import com.ppoppi.house.domain.model.Pet
import com.ppoppi.house.domain.model.SEX
import com.ppoppi.house.domain.model.SPECIES
import com.ppoppi.house.ui.main.home.component.DiagnosisCard
import com.ppoppi.house.ui.main.home.component.PetAddButton
import com.ppoppi.house.ui.main.home.component.PetButton
import com.ppoppi.house.ui.main.setting.edit.PetInfoActivity
import com.ppoppi.house.ui.theme.Black
import com.ppoppi.house.ui.theme.PpoPpiTheme
import com.ppoppi.house.ui.theme.Primary200
import com.ppoppi.house.ui.theme.Primary400
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun HomeScreen(
    navigateToDiagnosis: () -> Unit,
    modifier: Modifier = Modifier,
    date: LocalDate = LocalDate.now(),
) {
    val context = LocalContext.current
    val pets =
        listOf(
            Pet(
                name = "뽀삐",
                species = SPECIES.DOG,
                breed = "뽀삐",
                age = 1,
                sex = SEX.MALE,
                color = COLOR.PRIMARY50,
            ),
            Pet(
                name = "뽀빠",
                species = SPECIES.CAT,
                breed = "뽀삐",
                age = 1,
                sex = SEX.MALE,
                color = COLOR.PRIMARY50,
            ),
        )
    var selectedPet by remember { mutableStateOf(pets.first()) }
    val diagnosisResult: Int? by remember { mutableStateOf(null) }

    Box(
        modifier =
            modifier
                .fillMaxSize()
                .padding(18.dp),
        contentAlignment = Alignment.TopEnd,
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_home),
            contentDescription = null,
            modifier = Modifier,
            tint = Primary200,
        )
    }
    Column(
        modifier =
            modifier
                .padding(top = 26.dp)
                .padding(horizontal = 26.dp),
    ) {
        Text(
            text = "홈",
            style = PpoPpiTheme.typography.headline1,
            color = Black,
        )

        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
                    .padding(top = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            pets.forEach { pet ->
                PetButton(
                    onClick = { selectedPet = pet },
                    pet = pet,
                    isSelected = selectedPet == pet,
                )
            }

            if (pets.size < 3) {
                PetAddButton(
                    onClick = {
                        val intent = PetInfoActivity.newIntent(context)
                        context.startActivity(intent)
                    },
                )
            }
        }

        Text(
            text = "${date.monthValue}월 ${date.dayOfMonth}일 (${
                date.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.KOREAN)
            })",
            style = PpoPpiTheme.typography.title1,
            color = Primary400,
            modifier = Modifier.padding(top = 12.dp),
        )

        if (diagnosisResult == null) {
            DiagnosisCard(
                onClick = (navigateToDiagnosis),
                name = selectedPet.name,
                modifier = Modifier.padding(top = 18.dp),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    PpoPpiTheme {
        HomeScreen(navigateToDiagnosis = {})
    }
}
