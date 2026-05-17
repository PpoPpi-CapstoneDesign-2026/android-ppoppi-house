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

    private val 병원 = HospitalInfo(
        id = 1L,
        name = "뽀삐동물병원",
        address = "서울시 강남구 테헤란로 123",
        callNumber = "02-1234-5678",
        businessHours = "09:00 - 18:00",
        operationLabel = "영업 중",
        distanceMeter = 350L,
        is24Hour = false,
    )

    @Test
    fun `병원명이_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                VetHospitalBottomSheet(hospital = 병원)
            }
        }
        composeTestRule.onNodeWithText("뽀삐동물병원").assertIsDisplayed()
    }

    @Test
    fun `병원_주소가_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                VetHospitalBottomSheet(hospital = 병원)
            }
        }
        composeTestRule.onNodeWithText("서울시 강남구 테헤란로 123").assertIsDisplayed()
    }

    @Test
    fun `병원_전화번호가_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                VetHospitalBottomSheet(hospital = 병원)
            }
        }
        composeTestRule.onNodeWithText("02-1234-5678").assertIsDisplayed()
    }

    @Test
    fun `병원_영업시간이_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                VetHospitalBottomSheet(hospital = 병원)
            }
        }
        composeTestRule.onNodeWithText("09:00 - 18:00").assertIsDisplayed()
    }

    @Test
    fun `병원까지의_거리가_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                VetHospitalBottomSheet(hospital = 병원)
            }
        }
        composeTestRule.onNodeWithText("350m").assertIsDisplayed()
    }

    @Test
    fun `영업_상태_라벨이_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                VetHospitalBottomSheet(hospital = 병원)
            }
        }
        composeTestRule.onNodeWithText("영업 중").assertIsDisplayed()
    }

    @Test
    fun `바로_전화하기_버튼이_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                VetHospitalBottomSheet(hospital = 병원)
            }
        }
        composeTestRule.onNodeWithText("바로 전화하기").assertIsDisplayed()
    }
}
