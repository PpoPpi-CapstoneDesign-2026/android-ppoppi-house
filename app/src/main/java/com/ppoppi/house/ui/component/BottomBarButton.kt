package com.ppoppi.house.ui.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ppoppi.house.ui.theme.PpoPpiTheme
import com.ppoppi.house.ui.theme.Primary400
import com.ppoppi.house.ui.theme.White

@Composable
fun BottomBarButton(
    onClick: () -> Unit,
    text: String,
    backgroundColor: Color = Primary400,
    textColor: Color = White,
    textStyle: TextStyle = PpoPpiTheme.typography.title1,
) {
    Button(
        onClick = onClick,
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .padding(bottom = 40.dp),
        shape = RoundedCornerShape(8.dp),
        colors =
            ButtonDefaults.buttonColors(
                containerColor = backgroundColor,
            ),
        contentPadding = PaddingValues(vertical = 12.dp),
    ) {
        Text(
            text = text,
            style = textStyle,
            color = textColor,
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun BottomBarButtonPreview() {
    PpoPpiTheme {
        BottomBarButton(onClick = {}, text = "시작하기")
    }
}
