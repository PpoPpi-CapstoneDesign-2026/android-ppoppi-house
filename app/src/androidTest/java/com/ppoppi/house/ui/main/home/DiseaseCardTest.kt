package com.ppoppi.house.ui.main.home

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ppoppi.house.domain.model.Disease
import com.ppoppi.house.ui.main.home.component.DiseaseCard
import com.ppoppi.house.ui.theme.PpoPpiTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DiseaseCardTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val 질병 = Disease(
        diseaseId = 1L,
        name = "결막염",
        breed = "말티즈",
        description = "눈의 결막에 염증이 생기는 질환입니다",
    )

    @Test
    fun `질병명이_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                DiseaseCard(disease = 질병)
            }
        }
        composeTestRule.onNodeWithText("🏥   결막염").assertIsDisplayed()
    }

    @Test
    fun `품종_라벨이_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                DiseaseCard(disease = 질병)
            }
        }
        composeTestRule.onNodeWithText("말티즈").assertIsDisplayed()
    }

    @Test
    fun `질병_설명이_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                DiseaseCard(disease = 질병)
            }
        }
        composeTestRule.onNodeWithText("눈의 결막에 염증이 생기는 질환입니다").assertIsDisplayed()
    }
}
