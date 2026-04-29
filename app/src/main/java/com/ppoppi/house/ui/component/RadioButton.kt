package com.ppoppi.house.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ppoppi.house.ui.theme.Black
import com.ppoppi.house.ui.theme.Gray100
import com.ppoppi.house.ui.theme.PpoPpiTheme
import com.ppoppi.house.ui.theme.Primary400
import com.ppoppi.house.ui.theme.Primary50
import com.ppoppi.house.ui.theme.White
import com.ppoppi.house.ui.util.noRippleClickable

@Composable
fun RadioButton(
    onClick: () -> Unit,
    text: String,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier =
            modifier
                .height(50.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(
                    if (isSelected) Primary50 else White,
                ).noRippleClickable(onClick = onClick)
                .border(
                    width = 1.dp,
                    color = if (isSelected) Primary400 else Gray100,
                    shape = RoundedCornerShape(8.dp),
                ).padding(vertical = 12.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            style = PpoPpiTheme.typography.title2,
            color = Black,
        )
    }
}

@Composable
@Preview(showBackground = true, name = "선택된 라디오 버튼")
private fun RadioButtonPreview_Selected() {
    PpoPpiTheme {
        RadioButton(
            onClick = { },
            text = "라디오 버튼",
            isSelected = true,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
        )
    }
}

@Composable
@Preview(showBackground = true, name = "선택 해제된 라디오 버튼")
private fun RadioButtonPreview_Unselected() {
    PpoPpiTheme {
        RadioButton(
            onClick = { },
            text = "라디오 버튼",
            isSelected = false,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
        )
    }
}
