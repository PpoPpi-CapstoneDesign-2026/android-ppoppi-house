package com.ppoppi.house.ui.main.setting

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ppoppi.house.domain.model.COLOR
import com.ppoppi.house.domain.model.Pet
import com.ppoppi.house.domain.model.SEX
import com.ppoppi.house.domain.model.SPECIES
import com.ppoppi.house.ui.component.PetItem
import com.ppoppi.house.ui.main.setting.component.DeleteAccountDialog
import com.ppoppi.house.ui.main.setting.component.LogoutDialog
import com.ppoppi.house.ui.main.setting.edit.PetInfoActivity
import com.ppoppi.house.ui.onboarding.list.component.AddPetButton
import com.ppoppi.house.ui.theme.Black
import com.ppoppi.house.ui.theme.Gray100
import com.ppoppi.house.ui.theme.Gray400
import com.ppoppi.house.ui.theme.PpoPpiTheme
import com.ppoppi.house.ui.util.noRippleClickable

@Composable
fun SettingScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var showLogoutDialog by remember { mutableStateOf(false) }
    var showAccountDeleteDialog by remember { mutableStateOf(false) }
    val pets =
        listOf(
            Pet(
                name = "뽀삐",
                species = SPECIES.DOG,
                breed = "뽀삐",
                age = 1,
                sex = SEX.MALE,
                color = COLOR.PRIMARY50,
            ),
            Pet(
                name = "뽀빠",
                species = SPECIES.CAT,
                breed = "뽀삐",
                age = 1,
                sex = SEX.MALE,
                color = COLOR.PRIMARY200,
            ),
        )

    Column(
        modifier =
            modifier
                .fillMaxSize()
                .padding(horizontal = 26.dp)
                .padding(top = 24.dp)
                .verticalScroll(rememberScrollState()),
    ) {
        Text(
            text = "설정 ⚙️",
            style = PpoPpiTheme.typography.headline1,
            color = Black,
        )

        Text(
            text = "권한 설정",
            style = PpoPpiTheme.typography.title3,
            color = Black,
            modifier = Modifier.padding(top = 20.dp),
        )
        Column(
            modifier =
                Modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .border(1.dp, Gray100, RoundedCornerShape(8.dp)),
        ) {
            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp, horizontal = 18.dp)
                        .noRippleClickable(onClick = {
                            context.startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
                        }),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "위치 권한 설정하러 가기",
                    style = PpoPpiTheme.typography.title3,
                    color = Black,
                )
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                    contentDescription = null,
                    tint = Gray400,
                )
            }

            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 1.dp,
                color = Gray100,
            )
            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp, horizontal = 18.dp)
                        .noRippleClickable(onClick = {
                            context.startActivity(
                                Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                                    data = Uri.fromParts("package", context.packageName, null)
                                },
                            )
                        }),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "카메라 권한 설정하러 가기",
                    style = PpoPpiTheme.typography.title3,
                    color = Black,
                )
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                    contentDescription = null,
                    tint = Gray400,
                )
            }
        }

        Text(
            text = "계정 설정",
            style = PpoPpiTheme.typography.title3,
            color = Black,
            modifier = Modifier.padding(top = 30.dp),
        )
        Column(
            modifier =
                Modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .border(1.dp, Gray100, RoundedCornerShape(8.dp)),
        ) {
            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp, horizontal = 18.dp)
                        .noRippleClickable(onClick = { showLogoutDialog = true }),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "로그아웃",
                    style = PpoPpiTheme.typography.title3,
                    color = Black,
                )
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                    contentDescription = null,
                    tint = Gray400,
                )
            }

            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 1.dp,
                color = Gray100,
            )
            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp, horizontal = 18.dp)
                        .noRippleClickable(onClick = { showAccountDeleteDialog = true }),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "회원 탈퇴",
                    style = PpoPpiTheme.typography.title3,
                    color = Black,
                )
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                    contentDescription = null,
                    tint = Gray400,
                )
            }
        }

        Text(
            text = "반려동물 설정",
            style = PpoPpiTheme.typography.title3,
            color = Black,
            modifier = Modifier.padding(top = 30.dp),
        )

        Column(
            modifier =
                Modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth(),
        ) {
            pets.forEach { pet ->
                PetItem(
                    pet = pet,
                    onClick = {
                        val intent = PetInfoActivity.newIntent(context, pet)
                        context.startActivity(intent)
                    },
                )
                Spacer(modifier = Modifier.height(10.dp))
            }

            if (pets.size < 3) {
                AddPetButton(
                    onClick = {
                        val intent = PetInfoActivity.newIntent(context)
                        context.startActivity(intent)
                    },
                )
            }

            Text(
                text = "반려동물은 3마리까지 등록할 수 있어요",
                style = PpoPpiTheme.typography.label1,
                color = Gray100,
                modifier = Modifier.padding(top = 10.dp, bottom = 24.dp),
            )
        }
    }

    if (showLogoutDialog) {
        LogoutDialog(
            onConfirm = {
                // TODO: 로그아웃 로직 추가
            },
            onDismiss = { showLogoutDialog = false },
        )
    }

    if (showAccountDeleteDialog) {
        DeleteAccountDialog(
            onConfirm = {
                // TODO: 회원 탈퇴 로직 추가
            },
            onDismiss = { showAccountDeleteDialog = false },
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SettingScreenPreview() {
    PpoPpiTheme {
        SettingScreen()
    }
}
