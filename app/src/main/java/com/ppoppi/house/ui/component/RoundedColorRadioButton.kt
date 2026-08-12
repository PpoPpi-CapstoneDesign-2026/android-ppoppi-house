package com.ppoppi.house.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ppoppi.house.ui.theme.Gray400
import com.ppoppi.house.ui.theme.PpoPpiTheme
import com.ppoppi.house.ui.theme.Primary100
import com.ppoppi.house.ui.util.noRippleClickable

@Composable
fun RoundedColorRadioButton(
    onClick: () -> Unit,
    isSelected: Boolean,
    background: Color,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier =
            modifier
                .size(50.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(color = background, shape = RoundedCornerShape(100.dp))
                .noRippleClickable(onClick = onClick)
                .border(
                    width = 2.dp,
                    color = if (isSelected) Gray400 else background,
                    shape = RoundedCornerShape(100.dp),
                ).padding(vertical = 12.dp),
        contentAlignment = Alignment.Center,
    ) {
    }
}

@Composable
@Preview(showBackground = true, name = "선택된 색상 버튼")
private fun RoundedColorRadioButtonPreview_Selected() {
    PpoPpiTheme {
        RoundedColorRadioButton(
            onClick = {},
            isSelected = true,
            background = Primary100,
        )
    }
}

@Composable
@Preview(showBackground = true, name = "선택 해제된 색상 버튼")
private fun RoundedColorRadioButtonPreview_Unselected() {
    PpoPpiTheme {
        RoundedColorRadioButton(
            onClick = {},
            isSelected = false,
            background = Primary100,
        )
    }
}
