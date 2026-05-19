package com.ppoppi.house.ui.main.diary

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ppoppi.house.ui.main.diary.component.ChecklistItem
import com.ppoppi.house.ui.theme.PpoPpiTheme
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DiaryChecklistItemTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `체크리스트_아이템_텍스트가_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                ChecklistItem(checklist = "식욕 없음")
            }
        }
        composeTestRule.onNodeWithText("식욕 없음").assertIsDisplayed()
    }

    @Test
    fun `체크리스트_아이템_클릭_시_onClick_콜백이_호출된다`() {
        var clicked = false
        composeTestRule.setContent {
            PpoPpiTheme {
                ChecklistItem(checklist = "눈물 과다", onClick = { clicked = true })
            }
        }
        composeTestRule.onNodeWithText("눈물 과다").performClick()
        composeTestRule.runOnIdle { assertTrue(clicked) }
    }
}
