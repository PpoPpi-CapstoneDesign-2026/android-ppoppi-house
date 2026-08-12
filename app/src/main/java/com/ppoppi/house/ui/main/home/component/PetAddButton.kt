package com.ppoppi.house.ui.main.home.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ppoppi.house.ui.theme.Gray100
import com.ppoppi.house.ui.theme.Gray300
import com.ppoppi.house.ui.theme.PpoPpiTheme
import com.ppoppi.house.ui.util.noRippleClickable

@Composable
fun PetAddButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier =
            modifier
                .size(60.dp, 66.dp)
                .border(1.dp, Gray100, RoundedCornerShape(10.dp))
                .noRippleClickable(onClick),
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = null,
            tint = Gray100,
            modifier =
                Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 8.dp),
        )
        Text(
            text = "추가",
            style = PpoPpiTheme.typography.title3,
            modifier =
                Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 8.dp),
            color = Gray300,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PetAddButtonPreview() {
    PpoPpiTheme {
        PetAddButton(
            onClick = {},
        )
    }
}
