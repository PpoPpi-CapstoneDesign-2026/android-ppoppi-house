package com.ppoppi.house.ui.diagnosis.check

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ppoppi.house.ui.theme.PpoPpiTheme
import com.ppoppi.house.ui.util.noRippleClickable
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ChecklistScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    // ChecklistItem 컴포넌트 테스트

    @Test
    fun `체크되지_않은_증상_아이템에_설명_텍스트가_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                ChecklistItem(
                    description = "눈물이 많아요",
                    isChecked = false,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
        composeTestRule.onNodeWithText("눈물이 많아요").assertIsDisplayed()
    }

    @Test
    fun `체크된_증상_아이템에_설명_텍스트가_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                ChecklistItem(
                    description = "눈곱이 껴요",
                    isChecked = true,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
        composeTestRule.onNodeWithText("눈곱이 껴요").assertIsDisplayed()
    }

    @Test
    fun `증상_아이템_클릭_시_onClick_콜백이_호출된다`() {
        var clicked = false
        composeTestRule.setContent {
            PpoPpiTheme {
                ChecklistItem(
                    description = "눈이 충혈됐어요",
                    isChecked = false,
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .noRippleClickable(onClick = { clicked = true }),
                )
            }
        }
        composeTestRule.onNodeWithText("눈이 충혈됐어요").performClick()
        composeTestRule.runOnIdle { assertTrue(clicked) }
    }

    @Test
    fun `증상_아이템을_클릭하면_체크_상태가_토글된다`() {
        var isChecked by mutableStateOf(false)
        composeTestRule.setContent {
            PpoPpiTheme {
                ChecklistItem(
                    description = "눈이 뿌옇게 보여요",
                    isChecked = isChecked,
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .noRippleClickable(onClick = { isChecked = !isChecked }),
                )
            }
        }
        composeTestRule.onNodeWithText("눈이 뿌옇게 보여요").performClick()
        composeTestRule.runOnIdle { assertTrue(isChecked) }
    }
}
