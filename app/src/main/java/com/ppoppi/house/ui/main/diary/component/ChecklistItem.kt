package com.ppoppi.house.ui.main.diary.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ppoppi.house.ui.theme.PpoPpiTheme
import com.ppoppi.house.ui.theme.Primary200
import com.ppoppi.house.ui.theme.White

@Composable
fun ChecklistItem(
    checklist: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Primary200,
    borderColor: Color = Primary200,
    textColor: Color = White,
) {
    Row(
        modifier =
            modifier
                .clip(RoundedCornerShape(100.dp))
                .background(backgroundColor)
                .border(1.dp, borderColor, RoundedCornerShape(100.dp))
                .padding(vertical = 4.dp, horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = checklist,
            style = PpoPpiTheme.typography.body3,
            color = textColor,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ChecklistItemPreview() {
    PpoPpiTheme {
        ChecklistItem(checklist = "충혈")
    }
}
