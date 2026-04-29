package com.ppoppi.house.ui.onboarding.register.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ppoppi.house.R
import com.ppoppi.house.ui.component.RoundedColorRadioButton
import com.ppoppi.house.ui.theme.Black
import com.ppoppi.house.ui.theme.PpoPpiTheme
import com.ppoppi.house.ui.theme.Primary200
import com.ppoppi.house.ui.theme.Primary50
import com.ppoppi.house.ui.theme.Primary600

@Composable
fun ColorInputSection(
    color: Int,
    onValueChanged: (color: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        Text(
            text = stringResource(R.string.pet_register_color_input_section),
            color = Black,
            style = PpoPpiTheme.typography.title1,
        )

        Row(
            modifier = Modifier.padding(top = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            RoundedColorRadioButton(
                isSelected = color == 1,
                onClick = { onValueChanged(1) },
                background = Primary50,
                modifier = Modifier,
            )
            RoundedColorRadioButton(
                isSelected = color == 2,
                onClick = { onValueChanged(2) },
                background = Primary200,
                modifier = Modifier,
            )
            RoundedColorRadioButton(
                isSelected = color == 3,
                onClick = { onValueChanged(3) },
                background = Primary600,
                modifier = Modifier,
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun ColorInputSectionPreview() {
    PpoPpiTheme {
        ColorInputSection(color = 1, onValueChanged = {})
    }
}
