package com.ppoppi.house.ui.onboarding.list.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ppoppi.house.R
import com.ppoppi.house.ui.theme.Black
import com.ppoppi.house.ui.theme.Gray100
import com.ppoppi.house.ui.theme.PpoPpiTheme
import com.ppoppi.house.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PetListTopAppBar() {
    Column {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = stringResource(R.string.pet_list_top_bar),
                    style = PpoPpiTheme.typography.title1,
                )
            },
            colors =
                TopAppBarDefaults.topAppBarColors(
                    containerColor = White,
                    titleContentColor = Black,
                ),
        )
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = Gray100,
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun PetListScreenPreview() {
    PpoPpiTheme {
        PetListTopAppBar()
    }
}
