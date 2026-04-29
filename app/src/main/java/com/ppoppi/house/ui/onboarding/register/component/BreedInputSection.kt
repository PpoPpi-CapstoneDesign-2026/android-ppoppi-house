package com.ppoppi.house.ui.onboarding.register.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ppoppi.house.R
import com.ppoppi.house.ui.component.BreedDropdown
import com.ppoppi.house.ui.theme.Black
import com.ppoppi.house.ui.theme.PpoPpiTheme

@Composable
fun BreedInputSection(
    breed: String?,
    onValueChanged: (breed: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(R.string.pet_register_breed_input_section),
            color = Black,
            style = PpoPpiTheme.typography.title1,
        )

        BreedDropdown(
            selectedBreed = breed,
            breeds = listOf("aa", "bb", "cc", "dd", "ee", "ff", "gg"),
            onBreedSelected = { onValueChanged(it) },
            modifier = Modifier.padding(top = 16.dp),
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun BreedInputSectionPreview() {
    PpoPpiTheme {
        BreedInputSection(
            breed = "푸들",
            onValueChanged = {},
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun BreedInputSectionPreview_Null() {
    PpoPpiTheme {
        BreedInputSection(
            breed = null,
            onValueChanged = {},
        )
    }
}
