package com.ppoppi.house.ui.main.map

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ppoppi.house.ui.main.map.component.BusinessStatusLabel
import com.ppoppi.house.ui.theme.PpoPpiTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BusinessStatusLabelTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `영업_중_상태가_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                BusinessStatusLabel(businessStatus = "영업 중")
            }
        }
        composeTestRule.onNodeWithText("영업 중").assertIsDisplayed()
    }

    @Test
    fun `영업_종료_상태가_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                BusinessStatusLabel(businessStatus = "영업 종료")
            }
        }
        composeTestRule.onNodeWithText("영업 종료").assertIsDisplayed()
    }
}
