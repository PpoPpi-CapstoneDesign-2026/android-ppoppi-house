package com.ppoppi.house.ui.diagnosis.select

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ppoppi.house.R
import com.ppoppi.house.domain.model.COLOR
import com.ppoppi.house.domain.model.Pet
import com.ppoppi.house.domain.model.SEX
import com.ppoppi.house.domain.model.SPECIES
import com.ppoppi.house.ui.component.PetItem
import com.ppoppi.house.ui.theme.Black
import com.ppoppi.house.ui.theme.PpoPpiTheme
import com.ppoppi.house.ui.theme.White
import com.ppoppi.house.ui.util.getStyledText

@Composable
fun PetSelectScreen(
    onBackClick: () -> Unit,
    onSelect: (pet: Pet) -> Unit,
    pets: List<Pet>,
) {
    Scaffold(
        topBar = { PetSelectTopAppBar(onBackClick) },
        containerColor = White,
    ) { innerPadding ->
        Column(
            modifier =
                Modifier
                    .padding(innerPadding)
                    .padding(horizontal = 22.dp),
        ) {
            Text(
                text =
                    stringResource(R.string.diagnosis_select_hint).getStyledText(
                        style = PpoPpiTheme.typography.title2,
                        stringResource(R.string.diagnosis_select_hint_highlight),
                    ),
                style = PpoPpiTheme.typography.body2,
                color = Black,
                modifier = Modifier.padding(top = 20.dp),
            )

            LazyColumn(
                modifier = Modifier.padding(top = 26.dp),
            ) {
                items(pets.size) {
                    PetItem(pet = pets[it], onClick = { onSelect(pets[it]) })
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun PetSelectScreenPreview() {
    PpoPpiTheme {
        PetSelectScreen(
            onBackClick = {},
            onSelect = {},
            listOf(
                Pet(
                    name = "김은지",
                    species = SPECIES.DOG,
                    breed = "사람",
                    age = 24,
                    sex = SEX.FEMALE,
                    color = COLOR.PRIMARY200,
                ),
                Pet(
                    name = "김은지",
                    species = SPECIES.DOG,
                    breed = "사람",
                    age = 24,
                    sex = SEX.FEMALE,
                    color = COLOR.PRIMARY100,
                ),
                Pet(
                    name = "김은지",
                    species = SPECIES.DOG,
                    breed = "사람",
                    age = 24,
                    sex = SEX.FEMALE,
                    color = COLOR.PRIMARY600,
                ),
            ),
        )
    }
}
