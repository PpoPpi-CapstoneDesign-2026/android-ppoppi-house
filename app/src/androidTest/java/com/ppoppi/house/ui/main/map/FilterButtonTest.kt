package com.ppoppi.house.ui.main.map

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ppoppi.house.ui.main.map.component.FilterButton
import com.ppoppi.house.ui.theme.PpoPpiTheme
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FilterButtonTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `필터_버튼에_텍스트가_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                FilterButton(onClick = {}, text = "24시간", isSelected = false)
            }
        }
        composeTestRule.onNodeWithText("24시간").assertIsDisplayed()
    }

    @Test
    fun `선택된_필터_버튼에_텍스트가_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                FilterButton(onClick = {}, text = "영업 중", isSelected = true)
            }
        }
        composeTestRule.onNodeWithText("영업 중").assertIsDisplayed()
    }

    @Test
    fun `필터_버튼_클릭_시_onClick_콜백이_호출된다`() {
        var clicked = false
        composeTestRule.setContent {
            PpoPpiTheme {
                FilterButton(onClick = { clicked = true }, text = "24시간", isSelected = false)
            }
        }
        composeTestRule.onNodeWithText("24시간").performClick()
        composeTestRule.runOnIdle { assertTrue(clicked) }
    }
}
