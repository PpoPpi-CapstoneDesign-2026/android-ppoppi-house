package com.ppoppi.house.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ppoppi.house.ui.theme.Gray200
import com.ppoppi.house.ui.theme.Gray400
import com.ppoppi.house.ui.theme.PpoPpiTheme

@Composable
fun PpoPpiTextField(
    value: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    maxLength: Int = Int.MAX_VALUE,
    placeHolder: String = "",
    prefix: @Composable (Modifier) -> Unit = {},
    suffix: @Composable (Modifier) -> Unit = {},
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Box(
        modifier = modifier,
    ) {
        BasicTextField(
            value = value,
            onValueChange = { newValue ->
                if (newValue.length <= maxLength) onValueChanged(newValue)
            },
            textStyle = PpoPpiTheme.typography.body1.copy(color = Gray400),
            singleLine = true,
            decorationBox = { innerTextField ->
                Row {
                    prefix(
                        Modifier
                            .padding(end = 8.dp)
                            .align(Alignment.CenterVertically),
                    )

                    Box(
                        modifier = Modifier.weight(1f),
                    ) {
                        if (value.isEmpty()) {
                            Text(
                                text = placeHolder,
                                style = PpoPpiTheme.typography.body1,
                                color = Gray200,
                            )
                        }
                        innerTextField()
                    }

                    suffix(Modifier.padding(start = 16.dp))
                }
            },
            keyboardOptions = keyboardOptions,
            keyboardActions =
                KeyboardActions(onDone = {
                    keyboardController?.hide()
                }),
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun PpoPpiTextFieldPreview() {
    PpoPpiTheme {
        PpoPpiTextField(
            modifier = Modifier.padding(10.dp),
            value = "프리뷰 텍스트 필드",
            onValueChanged = {},
        )
    }
}
