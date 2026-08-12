package com.ppoppi.house.ui.main.map

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ppoppi.house.domain.model.HospitalInfo
import com.ppoppi.house.ui.main.map.component.VetHospitalBottomSheet
import com.ppoppi.house.ui.theme.PpoPpiTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class VetHospitalBottomSheetTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val hospital =
        HospitalInfo(
            id = 1L,
            name = "뽀삐동물hospital",
            address = "서울시 강남구 테헤란로 123",
            callNumber = "02-1234-5678",
            businessHours = "09:00 - 18:00",
            operationLabel = "영업 중",
            distanceMeter = 350L,
            is24Hour = false,
        )

    @Test
    fun `hospital명이_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                VetHospitalBottomSheet(hospital = hospital)
            }
        }
        composeTestRule.onNodeWithText("뽀삐동물hospital").assertIsDisplayed()
    }

    @Test
    fun `hospital_주소가_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                VetHospitalBottomSheet(hospital = hospital)
            }
        }
        composeTestRule.onNodeWithText("서울시 강남구 테헤란로 123").assertIsDisplayed()
    }

    @Test
    fun `hospital_전화번호가_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                VetHospitalBottomSheet(hospital = hospital)
            }
        }
        composeTestRule.onNodeWithText("02-1234-5678").assertIsDisplayed()
    }

    @Test
    fun `hospital_영업시간이_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                VetHospitalBottomSheet(hospital = hospital)
            }
        }
        composeTestRule.onNodeWithText("09:00 - 18:00").assertIsDisplayed()
    }

    @Test
    fun `hospital까지의_거리가_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                VetHospitalBottomSheet(hospital = hospital)
            }
        }
        composeTestRule.onNodeWithText("350m").assertIsDisplayed()
    }

    @Test
    fun `영업_상태_라벨이_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                VetHospitalBottomSheet(hospital = hospital)
            }
        }
        composeTestRule.onNodeWithText("영업 중").assertIsDisplayed()
    }

    @Test
    fun `바로_전화하기_버튼이_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                VetHospitalBottomSheet(hospital = hospital)
            }
        }
        composeTestRule.onNodeWithText("바로 전화하기").assertIsDisplayed()
    }
}
