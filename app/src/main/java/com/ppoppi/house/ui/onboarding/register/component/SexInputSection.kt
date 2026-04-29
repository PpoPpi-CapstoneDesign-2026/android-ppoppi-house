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
import com.ppoppi.house.domain.SEX
import com.ppoppi.house.ui.component.RadioButton
import com.ppoppi.house.ui.theme.Black
import com.ppoppi.house.ui.theme.PpoPpiTheme

@Composable
fun SexInputSection(
    sex: SEX,
    onValueChanged: (sex: SEX) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        Text(
            text = stringResource(R.string.pet_register_sex_input_section),
            color = Black,
            style = PpoPpiTheme.typography.title1,
        )

        Row(
            modifier = Modifier.padding(top = 24.dp),
        ) {
            val maleLabel = stringResource(R.string.pet_register_sex_input_male)
            val femaleLabel = stringResource(R.string.pet_register_sex_input_female)
            RadioButton(
                text = maleLabel,
                isSelected = sex == SEX.MALE,
                onClick = { onValueChanged(SEX.MALE) },
                modifier = Modifier.weight(1f),
            )
            Spacer(modifier = Modifier.width(10.dp))
            RadioButton(
                text = femaleLabel,
                isSelected = sex == SEX.FEMALE,
                onClick = { onValueChanged(SEX.FEMALE) },
                modifier = Modifier.weight(1f),
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun SexInputSectionPreview_Male() {
    PpoPpiTheme {
        SexInputSection(
            sex = SEX.MALE,
            onValueChanged = {},
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun SexInputSectionPreview_Female() {
    PpoPpiTheme {
        SexInputSection(
            sex = SEX.FEMALE,
            onValueChanged = {},
        )
    }
}
