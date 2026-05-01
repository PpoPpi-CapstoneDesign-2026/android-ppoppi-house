package com.ppoppi.house.ui.main.map.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ppoppi.house.domain.BusinessStatus
import com.ppoppi.house.domain.BusinessStatus.Companion.toColor
import com.ppoppi.house.ui.theme.PpoPpiTheme
import com.ppoppi.house.ui.theme.White

@Composable
fun BusinessStatusLabel(
    businessStatus: BusinessStatus,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier =
            modifier
                .clip(RoundedCornerShape(100.dp))
                .background(businessStatus.toColor())
                .padding(vertical = 4.dp, horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = businessStatus.label,
            style = PpoPpiTheme.typography.label1,
            color = White,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BusinessStatusLabelPreview() {
    PpoPpiTheme {
        BusinessStatusLabel(
            businessStatus = BusinessStatus.OPEN,
        )
    }
}
