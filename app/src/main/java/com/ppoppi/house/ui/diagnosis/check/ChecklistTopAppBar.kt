package com.ppoppi.house.ui.diagnosis.check

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ppoppi.house.R
import com.ppoppi.house.ui.component.SegmentedProgressBar
import com.ppoppi.house.ui.theme.Black
import com.ppoppi.house.ui.theme.Gray100
import com.ppoppi.house.ui.theme.Gray300
import com.ppoppi.house.ui.theme.PpoPpiTheme
import com.ppoppi.house.ui.theme.White
import com.ppoppi.house.ui.util.noRippleClickable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChecklistTopAppBar(onBackClick: () -> Unit) {
    Column {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = stringResource(R.string.diagnosis_check_top_bar),
                    style = PpoPpiTheme.typography.title1,
                )
            },
            navigationIcon = {
                Box(
                    modifier =
                        Modifier
                            .size(48.dp)
                            .noRippleClickable(onBackClick),
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBackIosNew,
                        contentDescription = stringResource(R.string.diagnosis_check_top_bar_navigation_description),
                        tint = Gray300,
                        modifier = Modifier.align(Alignment.Center),
                    )
                }
            },
            colors =
                TopAppBarDefaults.topAppBarColors(
                    containerColor = White,
                    titleContentColor = Black,
                    navigationIconContentColor = Gray100,
                ),
        )
        SegmentedProgressBar(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp)
                    .height(4.dp),
            currentProgress = 3,
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun ChecklistTopAppBarPreview() {
    PpoPpiTheme {
        ChecklistTopAppBar(onBackClick = {})
    }
}
