package com.ppoppi.house.ui.login

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ppoppi.house.ui.theme.PpoPpiTheme
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    // 버튼 표시

    @Test
    fun `카카오_로그인_버튼이_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                LoginScreen(toMain = {}, toOnboarding = {}, onKakaoLogin = {})
            }
        }
        composeTestRule.onNodeWithText("카카오 계정으로 로그인").assertIsDisplayed()
    }

    @Test
    fun `구글_로그인_버튼이_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                LoginScreen(toMain = {}, toOnboarding = {}, onKakaoLogin = {})
            }
        }
        composeTestRule.onNodeWithText("구글 계정으로 로그인").assertIsDisplayed()
    }

    @Test
    fun `네이버_로그인_버튼이_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                LoginScreen(toMain = {}, toOnboarding = {}, onKakaoLogin = {})
            }
        }
        composeTestRule.onNodeWithText("네이버 계정으로 로그인").assertIsDisplayed()
    }

    @Test
    fun `세_개의_로그인_버튼이_모두_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                LoginScreen(toMain = {}, toOnboarding = {}, onKakaoLogin = {})
            }
        }
        composeTestRule.onNodeWithText("카카오 계정으로 로그인").assertIsDisplayed()
        composeTestRule.onNodeWithText("구글 계정으로 로그인").assertIsDisplayed()
        composeTestRule.onNodeWithText("네이버 계정으로 로그인").assertIsDisplayed()
    }

    // 버튼 클릭 콜백

    @Test
    fun `카카오_로그인_버튼_클릭_시_onKakaoLogin_콜백이_호출된다`() {
        var called = false
        composeTestRule.setContent {
            PpoPpiTheme {
                LoginScreen(toMain = {}, toOnboarding = {}, onKakaoLogin = { called = true })
            }
        }
        composeTestRule.onNodeWithText("카카오 계정으로 로그인").performClick()
        composeTestRule.runOnIdle { assertTrue(called) }
    }

    @Test
    fun `구글_로그인_버튼_클릭_시_toOnboarding_콜백이_호출된다`() {
        var called = false
        composeTestRule.setContent {
            PpoPpiTheme {
                LoginScreen(toMain = {}, toOnboarding = { called = true }, onKakaoLogin = {})
            }
        }
        composeTestRule.onNodeWithText("구글 계정으로 로그인").performClick()
        composeTestRule.runOnIdle { assertTrue(called) }
    }

    @Test
    fun `네이버_로그인_버튼_클릭_시_toMain_콜백이_호출된다`() {
        var called = false
        composeTestRule.setContent {
            PpoPpiTheme {
                LoginScreen(toMain = { called = true }, toOnboarding = {}, onKakaoLogin = {})
            }
        }
        composeTestRule.onNodeWithText("네이버 계정으로 로그인").performClick()
        composeTestRule.runOnIdle { assertTrue(called) }
    }
}
