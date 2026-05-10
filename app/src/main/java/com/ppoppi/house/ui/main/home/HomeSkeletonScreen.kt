package com.ppoppi.house.ui.main.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ppoppi.house.ui.component.shimmerBrush
import com.ppoppi.house.ui.theme.PpoPpiTheme

@Composable
fun HomeSkeletonScreen(modifier: Modifier = Modifier) {
    val brush = shimmerBrush()

    Column(
        modifier = modifier
            .padding(top = 18.dp)
            .padding(horizontal = 18.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .size(40.dp, 28.dp)
                    .background(brush, RoundedCornerShape(4.dp)),
            )
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .background(brush, CircleShape),
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            repeat(2) {
                Box(
                    modifier = Modifier
                        .size(60.dp, 66.dp)
                        .background(brush, RoundedCornerShape(10.dp)),
                )
            }
        }

        Box(
            modifier = Modifier
                .padding(top = 12.dp)
                .size(130.dp, 20.dp)
                .background(brush, RoundedCornerShape(4.dp)),
        )

        Box(
            modifier = Modifier
                .padding(top = 18.dp)
                .fillMaxWidth()
                .height(240.dp)
                .background(brush, RoundedCornerShape(8.dp)),
        )

        Box(
            modifier = Modifier
                .padding(top = 30.dp)
                .size(80.dp, 20.dp)
                .background(brush, RoundedCornerShape(4.dp)),
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .height(48.dp)
                .background(brush, RoundedCornerShape(16.dp)),
        )

        Box(
            modifier = Modifier
                .padding(top = 16.dp, bottom = 18.dp)
                .fillMaxWidth()
                .height(160.dp)
                .background(brush, RoundedCornerShape(8.dp)),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeSkeletonScreenPreview() {
    PpoPpiTheme {
        HomeSkeletonScreen()
    }
}
