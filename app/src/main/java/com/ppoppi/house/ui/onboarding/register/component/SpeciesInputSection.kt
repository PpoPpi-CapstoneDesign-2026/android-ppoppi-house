package com.ppoppi.house.ui.onboarding.register.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ppoppi.house.R
import com.ppoppi.house.domain.model.SPECIES
import com.ppoppi.house.ui.component.RadioButton
import com.ppoppi.house.ui.theme.Black
import com.ppoppi.house.ui.theme.PpoPpiTheme

@Composable
fun SpeciesInputSection(
    species: SPECIES,
    onValueChanged: (species: SPECIES) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        Text(
            text = stringResource(R.string.pet_register_species_input_section),
            color = Black,
            style = PpoPpiTheme.typography.title1,
        )

        Row(
            modifier = Modifier.padding(top = 24.dp),
        ) {
            val dogLabel = stringResource(R.string.pet_register_species_input_dog)
            val catLabel = stringResource(R.string.pet_register_species_input_cat)

            RadioButton(
                text = dogLabel,
                isSelected = species == SPECIES.DOG,
                onClick = { onValueChanged(SPECIES.DOG) },
                modifier = Modifier.weight(1f),
            )
            Spacer(modifier = Modifier.width(10.dp))
            RadioButton(
                text = catLabel,
                isSelected = species == SPECIES.CAT,
                onClick = { onValueChanged(SPECIES.CAT) },
                modifier = Modifier.weight(1f),
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun SpeciesInputSectionPreview_Dog() {
    PpoPpiTheme {
        SpeciesInputSection(
            species = SPECIES.DOG,
            onValueChanged = {},
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun SpeciesInputSectionPreview_Cat() {
    PpoPpiTheme {
        SpeciesInputSection(
            species = SPECIES.CAT,
            onValueChanged = {},
        )
    }
}
