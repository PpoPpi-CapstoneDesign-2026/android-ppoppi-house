package com.ppoppi.house.ui.main.setting.edit.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ppoppi.house.R
import com.ppoppi.house.ui.component.PpoPpiTextField
import com.ppoppi.house.ui.theme.Black
import com.ppoppi.house.ui.theme.PpoPpiTheme

@Composable
fun NameInputSection(
    name: String,
    onValueChanged: (name: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        Text(
            text = stringResource(R.string.pet_register_name_input_section),
            color = Black,
            style = PpoPpiTheme.typography.title1,
        )

        PpoPpiTextField(
            value = name,
            onValueChanged = { onValueChanged(it) },
            placeHolder = stringResource(R.string.pet_register_name_input_hint),
            modifier = Modifier.padding(top = 16.dp),
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun NameInputSectionPreview() {
    PpoPpiTheme {
        NameInputSection(
            name = "뽀삐",
            onValueChanged = { },
        )
    }
}

@Composable
@Preview(showBackground = true, name = "입력칸이 비어 있는 경우")
private fun NameInputSectionPreview_Empty() {
    PpoPpiTheme {
        NameInputSection(
            name = "",
            onValueChanged = { },
        )
    }
}
