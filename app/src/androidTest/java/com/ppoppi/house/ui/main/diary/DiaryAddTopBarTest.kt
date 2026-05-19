package com.ppoppi.house.ui.main.diary

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ppoppi.house.ui.main.diary.component.DiaryAddTopBar
import com.ppoppi.house.ui.theme.PpoPpiTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DiaryAddTopBarTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `다이어리_추가_제목이_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                DiaryAddTopBar(onBackClick = {})
            }
        }
        composeTestRule.onNodeWithText("다이어리 추가").assertIsDisplayed()
    }
}
