package com.ppoppi.house.ui.diagnosis.camera

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ppoppi.house.ui.theme.PpoPpiTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CameraScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `반려동물_이름이_포함된_촬영_힌트_텍스트가_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                CameraScreen(onBackClick = {}, onCaptured = {}, name = "뽀삐")
            }
        }
        composeTestRule.onNodeWithText("뽀삐의 눈을 촬영해 주세요").assertIsDisplayed()
    }

    @Test
    fun `카메라_촬영_안내_문구가_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                CameraScreen(onBackClick = {}, onCaptured = {}, name = "뽀삐")
            }
        }
        composeTestRule
            .onNodeWithText(
                "1. 밝은 곳으로 이동해 주세요\n2. 카메라를 눈에서 10~15cm 거리에 두세요\n3. 초록 박스 안에 눈이 들어오면 촬영 버튼을 누르세요",
            ).assertIsDisplayed()
    }

    @Test
    fun `카메라_권한이_없을_때_권한_요청_버튼이_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                CameraScreen(onBackClick = {}, onCaptured = {}, name = "뽀삐")
            }
        }
        composeTestRule.onNodeWithText("카메라 권한 허용").assertIsDisplayed()
    }
}
