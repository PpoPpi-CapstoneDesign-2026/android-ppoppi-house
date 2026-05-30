package com.ppoppi.house.ui.main.setting.edit

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ppoppi.house.R
import com.ppoppi.house.domain.model.COLOR
import com.ppoppi.house.domain.model.Pet
import com.ppoppi.house.domain.model.SEX
import com.ppoppi.house.domain.model.SPECIES
import com.ppoppi.house.ui.component.BottomBarButton
import com.ppoppi.house.ui.main.setting.component.DeletePetDialog
import com.ppoppi.house.ui.onboarding.register.component.AgeInputSection
import com.ppoppi.house.ui.onboarding.register.component.BreedInputSection
import com.ppoppi.house.ui.onboarding.register.component.ColorInputSection
import com.ppoppi.house.ui.onboarding.register.component.NameInputSection
import com.ppoppi.house.ui.onboarding.register.component.PetRegisterTopAppBar
import com.ppoppi.house.ui.onboarding.register.component.SexInputSection
import com.ppoppi.house.ui.onboarding.register.component.SpeciesInputSection
import com.ppoppi.house.ui.theme.Gray300
import com.ppoppi.house.ui.theme.PpoPpiTheme
import com.ppoppi.house.ui.theme.White
import com.ppoppi.house.ui.util.noRippleClickable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PetInfoScreen(
    onRegistered: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    pet: Pet? = null,
    viewModel: PetInfoViewModel = hiltViewModel(),
) {
    var showDeletePetDialog by rememberSaveable { mutableStateOf(false) }
    var name by rememberSaveable { mutableStateOf(pet?.name ?: "") }
    var species by rememberSaveable { mutableStateOf(pet?.species ?: SPECIES.DOG) }
    var breed: String? by rememberSaveable { mutableStateOf(pet?.breed) }
    var sex by rememberSaveable { mutableStateOf(pet?.sex ?: SEX.MALE) }
    var age by rememberSaveable { mutableIntStateOf(pet?.age ?: 1) }
    var color by rememberSaveable { mutableIntStateOf(pet?.color?.ordinal ?: 1) }

    val breeds by viewModel.breeds.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(viewModel) {
        viewModel.isRegistered.collect {
            Toast
                .makeText(
                    context,
                    if (pet == null) "반려동물이 추가됐어요" else "반려동물 정보가 수정됐어요",
                    Toast.LENGTH_SHORT,
                ).show()
            onRegistered()
        }
    }

    LaunchedEffect(viewModel) {
        viewModel.isDeleted.collect {
            Toast.makeText(context, "반려동물이 삭제됐어요", Toast.LENGTH_SHORT).show()
            onRegistered()
        }
    }

    LaunchedEffect(species) {
        viewModel.loadBreeds(species)
    }

    val title =
        if (pet == null) stringResource(R.string.pet_register_top_bar) else stringResource(R.string.pet_edit_top_bar)

    Scaffold(
        topBar = { PetRegisterTopAppBar(title, onBackClick) },
        modifier =
            modifier
                .background(White),
        containerColor = White,
        bottomBar = {
            BottomBarButton(
                onClick = {
                    val petData =
                        Pet(
                            name = name,
                            species = species,
                            breed = breed ?: "말티즈",
                            age = age,
                            sex = sex,
                            color = COLOR.entries[color],
                        )
                    if (pet?.id != null) {
                        viewModel.putPet(pet.id, petData)
                    } else {
                        viewModel.registerPet(petData)
                    }
                },
                text = stringResource(R.string.pet_register_bottom_bar_button),
                content = {
                    if (pet != null) {
                        Text(
                            text = "삭제하기",
                            style = PpoPpiTheme.typography.label1,
                            color = Gray300,
                            modifier = Modifier.noRippleClickable(onClick = { showDeletePetDialog = true }),
                        )
                    }
                },
            )
        },
    ) { innerPadding ->
        Column(
            modifier =
                Modifier
                    .padding(innerPadding)
                    .padding(horizontal = 30.dp)
                    .verticalScroll(rememberScrollState()),
        ) {
            Spacer(modifier = Modifier.height(30.dp))

            // 이름 입력 섹션
            NameInputSection(
                name = name,
                onValueChanged = { name = it },
            )

            Spacer(modifier = Modifier.height(30.dp))

            // 종류 입력 섹션
            SpeciesInputSection(
                species = species,
                onValueChanged = {
                    species = it
                    breed = null
                },
            )

            Spacer(modifier = Modifier.height(30.dp))

            // 품종 입력 섹션
            BreedInputSection(
                breed = breed,
                breeds = breeds,
                onValueChanged = { breed = it },
            )

            Spacer(modifier = Modifier.height(30.dp))

            // 성별 입력 섹션
            SexInputSection(
                sex = sex,
                onValueChanged = { sex = it },
            )

            Spacer(modifier = Modifier.height(30.dp))

            // 나이 입력 섹션
            AgeInputSection(
                onDecreaseAge = { age-- },
                onIncreaseAge = { age++ },
                age = age,
            )

            Spacer(modifier = Modifier.height(30.dp))

            // 색상 선택 섹션
            ColorInputSection(
                color = color,
                onValueChanged = { color = it },
            )

            Spacer(modifier = Modifier.height(20.dp))
        }
    }

    if (showDeletePetDialog) {
        DeletePetDialog(
            onConfirm = {
                showDeletePetDialog = false
                pet?.id?.let { viewModel.deletePet(it) }
            },
            onDismiss = { showDeletePetDialog = false },
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PetInfoScreenPreview() {
    PpoPpiTheme {
        PetInfoScreen(
            onRegistered = {},
            onBackClick = {},
            pet =
                Pet(
                    name = "뽀삐",
                    species = SPECIES.DOG,
                    breed = "뽀삐",
                    age = 1,
                    sex = SEX.MALE,
                    color = COLOR.PRIMARY100,
                ),
        )
    }
}
