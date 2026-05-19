package com.ppoppi.house.ui.splash

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onRoot
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ppoppi.house.ui.theme.PpoPpiTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SplashScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `스플래시_화면이_크래시_없이_렌더링된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                SplashScreen()
            }
        }
        composeTestRule.onRoot().assertIsDisplayed()
    }
}
