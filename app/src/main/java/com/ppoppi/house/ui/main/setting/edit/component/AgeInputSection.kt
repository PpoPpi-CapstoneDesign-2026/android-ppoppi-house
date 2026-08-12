package com.ppoppi.house.ui.main.setting.edit.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ppoppi.house.R
import com.ppoppi.house.ui.theme.Black
import com.ppoppi.house.ui.theme.Gray100
import com.ppoppi.house.ui.theme.PpoPpiTheme
import com.ppoppi.house.ui.util.noRippleClickable

@Composable
fun AgeInputSection(
    onDecreaseAge: () -> Unit,
    onIncreaseAge: () -> Unit,
    age: Int,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        Text(
            text = stringResource(R.string.pet_register_age_input_section),
            color = Black,
            style = PpoPpiTheme.typography.title1,
        )

        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier =
                    Modifier
                        .size(50.dp)
                        .noRippleClickable({ if (age > 1) onDecreaseAge() })
                        .border(
                            width = 1.dp,
                            shape = RoundedCornerShape(8.dp),
                            color = Gray100,
                        ),
            ) {
                Icon(
                    imageVector = Icons.Default.Remove,
                    contentDescription = stringResource(R.string.pet_register_age_decrease_button_description),
                    tint = Black,
                    modifier = Modifier.align(Alignment.Center),
                )
            }

            Text(
                text = stringResource(R.string.pet_register_age_input, age),
                style = PpoPpiTheme.typography.headline1,
                color = Black,
            )

            Box(
                modifier =
                    Modifier
                        .size(50.dp)
                        .noRippleClickable({ onIncreaseAge() })
                        .border(
                            width = 1.dp,
                            shape = RoundedCornerShape(8.dp),
                            color = Gray100,
                        ),
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.pet_register_age_increase_button_description),
                    tint = Black,
                    modifier = Modifier.align(Alignment.Center),
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun AgeInputSectionPreview() {
    PpoPpiTheme {
        AgeInputSection(
            onDecreaseAge = { },
            onIncreaseAge = { },
            age = 2,
        )
    }
}
