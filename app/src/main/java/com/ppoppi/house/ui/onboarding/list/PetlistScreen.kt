package com.ppoppi.house.ui.onboarding.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ppoppi.house.R
import com.ppoppi.house.domain.Pet
import com.ppoppi.house.ui.component.BottomBarButton
import com.ppoppi.house.ui.component.PetItem
import com.ppoppi.house.ui.onboarding.list.component.AddPetButton
import com.ppoppi.house.ui.onboarding.list.component.PetListTopAppBar
import com.ppoppi.house.ui.theme.Black
import com.ppoppi.house.ui.theme.PpoPpiTheme
import com.ppoppi.house.ui.theme.White

@Composable
fun PetListScreen(
    onStart: () -> Unit,
    onEdit: (pet: Pet) -> Unit,
    onRegister: () -> Unit,
    pets: List<Pet>,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = { PetListTopAppBar() },
        modifier = modifier.background(White),
        containerColor = White,
        bottomBar = {
            BottomBarButton(
                onClick = onStart,
                text = stringResource(R.string.pet_list_bottom_bar_button),
            )
        },
    ) { innerPadding ->
        Box(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 30.dp),
        ) {
            Column(modifier = Modifier.padding(top = 30.dp)) {
                Text(
                    text = stringResource(R.string.pet_list_registered_pet),
                    style = PpoPpiTheme.typography.title1,
                    color = Black,
                )

                LazyColumn {
                    items(pets.size) { item ->
                        PetItem(
                            modifier = Modifier.padding(top = 16.dp),
                            pet = pets[item],
                            onClick = { onEdit(pets[item]) },
                        )
                    }

                    if (pets.size < 3) {
                        item {
                            Spacer(modifier = Modifier.size(16.dp))
                            AddPetButton(onRegister)
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun PetListScreenPreview() {
    PpoPpiTheme {
        PetListScreen(
            onStart = {},
            onEdit = {},
            onRegister = {},
            pets = listOf(),
        )
    }
}
