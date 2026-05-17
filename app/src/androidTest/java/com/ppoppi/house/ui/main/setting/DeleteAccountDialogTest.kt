package com.ppoppi.house.ui.main.setting

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ppoppi.house.ui.main.setting.component.DeleteAccountDialog
import com.ppoppi.house.ui.theme.PpoPpiTheme
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DeleteAccountDialogTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `회원탈퇴_확인_문구가_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                DeleteAccountDialog(onConfirm = {}, onDismiss = {})
            }
        }
        composeTestRule.onNodeWithText("회원 탈퇴 하시겠습니까?").assertIsDisplayed()
    }

    @Test
    fun `회원탈퇴_버튼이_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                DeleteAccountDialog(onConfirm = {}, onDismiss = {})
            }
        }
        composeTestRule.onNodeWithText("회원 탈퇴").assertIsDisplayed()
    }

    @Test
    fun `취소_버튼이_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                DeleteAccountDialog(onConfirm = {}, onDismiss = {})
            }
        }
        composeTestRule.onNodeWithText("취소").assertIsDisplayed()
    }

    @Test
    fun `회원탈퇴_버튼_클릭_시_onConfirm_콜백이_호출된다`() {
        var called = false
        composeTestRule.setContent {
            PpoPpiTheme {
                DeleteAccountDialog(onConfirm = { called = true }, onDismiss = {})
            }
        }
        composeTestRule.onNodeWithText("회원 탈퇴").performClick()
        composeTestRule.runOnIdle { assertTrue(called) }
    }

    @Test
    fun `취소_버튼_클릭_시_onDismiss_콜백이_호출된다`() {
        var called = false
        composeTestRule.setContent {
            PpoPpiTheme {
                DeleteAccountDialog(onConfirm = {}, onDismiss = { called = true })
            }
        }
        composeTestRule.onNodeWithText("취소").performClick()
        composeTestRule.runOnIdle { assertTrue(called) }
    }
}
