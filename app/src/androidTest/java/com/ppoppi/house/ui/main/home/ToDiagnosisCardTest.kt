package com.ppoppi.house.ui.main.home

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ppoppi.house.ui.main.home.component.ToDiagnosisCard
import com.ppoppi.house.ui.theme.PpoPpiTheme
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ToDiagnosisCardTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `반려동물_이름이_포함된_안내_문구가_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                ToDiagnosisCard(onClick = {}, name = "뽀삐")
            }
        }
        composeTestRule.onNodeWithText("사진 한 장으로 뽀삐 눈 건강 체크!").assertIsDisplayed()
    }

    @Test
    fun `진단하러_가기_버튼이_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                ToDiagnosisCard(onClick = {}, name = "뽀삐")
            }
        }
        composeTestRule.onNodeWithText("진단하러 가기").assertIsDisplayed()
    }

    @Test
    fun `진단하러_가기_버튼_클릭_시_onClick_콜백이_호출된다`() {
        var clicked = false
        composeTestRule.setContent {
            PpoPpiTheme {
                ToDiagnosisCard(onClick = { clicked = true }, name = "뽀삐")
            }
        }
        composeTestRule.onNodeWithText("진단하러 가기").performClick()
        composeTestRule.runOnIdle { assertTrue(clicked) }
    }
}
