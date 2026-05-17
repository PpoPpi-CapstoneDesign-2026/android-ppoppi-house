package com.ppoppi.house.ui.main.home

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ppoppi.house.domain.model.Diagnosis
import com.ppoppi.house.domain.model.Triage
import com.ppoppi.house.ui.main.home.component.DiagnosisCard
import com.ppoppi.house.ui.theme.PpoPpiTheme
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DiagnosisCardTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val 진단결과 = Diagnosis(
        hasDiagnosis = true,
        imageUrl = null,
        triage = Triage.SOON,
        diseaseName = "결막염",
        affectedArea = "각막",
        triageConfidence = 85,
        guideAction = "동물병원 방문 권장",
        guideMessage = "조기 치료가 중요합니다",
        guideWarning = "방치 시 악화될 수 있어요",
    )

    @Test
    fun `질병명과_부위가_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                DiagnosisCard(navigateToDiagnosis = {}, diagnosis = 진단결과)
            }
        }
        composeTestRule.onNodeWithText("결막염 | 각막").assertIsDisplayed()
    }

    @Test
    fun `안내_메시지가_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                DiagnosisCard(navigateToDiagnosis = {}, diagnosis = 진단결과)
            }
        }
        composeTestRule.onNodeWithText("조기 치료가 중요합니다").assertIsDisplayed()
    }

    @Test
    fun `신뢰도_퍼센트가_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                DiagnosisCard(navigateToDiagnosis = {}, diagnosis = 진단결과)
            }
        }
        composeTestRule.onNodeWithText("85%").assertIsDisplayed()
    }

    @Test
    fun `다시_진단하기_버튼이_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                DiagnosisCard(navigateToDiagnosis = {}, diagnosis = 진단결과)
            }
        }
        composeTestRule.onNodeWithText("다시 진단하기").assertIsDisplayed()
    }

    @Test
    fun `다시_진단하기_버튼_클릭_시_navigateToDiagnosis_콜백이_호출된다`() {
        var called = false
        composeTestRule.setContent {
            PpoPpiTheme {
                DiagnosisCard(navigateToDiagnosis = { called = true }, diagnosis = 진단결과)
            }
        }
        composeTestRule.onNodeWithText("다시 진단하기").performClick()
        composeTestRule.runOnIdle { assertTrue(called) }
    }
}
