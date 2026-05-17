package com.ppoppi.house.ui.diagnosis.result

import android.net.Uri
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ppoppi.house.domain.model.Diagnosis
import com.ppoppi.house.domain.model.Triage
import com.ppoppi.house.ui.theme.PpoPpiTheme
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ResultScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private fun createDiagnosis(triage: Triage = Triage.NORMAL) = Diagnosis(
        imageUrl = null,
        triage = triage,
        diseaseName = "백내장",
        affectedArea = "왼쪽 눈",
        triageConfidence = 85,
        guideAction = "빠른 내원 권장",
        guideMessage = "즉시 동물병원에 방문해 주세요",
        guideWarning = "방치하면 악화될 수 있어요",
    )

    // 로딩 상태

    @Test
    fun `로딩_상태에서_반려동물_이름이_포함된_분석_중_텍스트가_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                ResultScreen(
                    petName = "뽀삐",
                    uiState = ResultUiState.Loading,
                    imageUri = Uri.EMPTY,
                    navigateToHome = {},
                    navigateToMap = {},
                    navigateToDiagnosis = {},
                )
            }
        }
        composeTestRule.onNodeWithText("뽀삐의 눈을 분석하고 있어요.").assertIsDisplayed()
    }

    @Test
    fun `로딩_상태에서_AI_분석_중_안내_텍스트가_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                ResultScreen(
                    petName = "뽀삐",
                    uiState = ResultUiState.Loading,
                    imageUri = Uri.EMPTY,
                    navigateToHome = {},
                    navigateToMap = {},
                    navigateToDiagnosis = {},
                )
            }
        }
        composeTestRule.onNodeWithText("AI 모델이 안구 이미지와\n증상을 함께 분석중입니다").assertIsDisplayed()
    }

    // 에러 상태

    @Test
    fun `에러_상태에서_AI_분석_실패_텍스트가_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                ResultScreen(
                    petName = "뽀삐",
                    uiState = ResultUiState.Error,
                    imageUri = Uri.EMPTY,
                    navigateToHome = {},
                    navigateToMap = {},
                    navigateToDiagnosis = {},
                )
            }
        }
        composeTestRule.onNodeWithText("AI 분석에 실패했어요").assertIsDisplayed()
    }

    @Test
    fun `에러_상태에서_재진단_안내_텍스트가_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                ResultScreen(
                    petName = "뽀삐",
                    uiState = ResultUiState.Error,
                    imageUri = Uri.EMPTY,
                    navigateToHome = {},
                    navigateToMap = {},
                    navigateToDiagnosis = {},
                )
            }
        }
        composeTestRule.onNodeWithText("다시 진단해 주세요").assertIsDisplayed()
    }

    @Test
    fun `에러_상태에서_다시_진단하기_버튼이_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                ResultScreen(
                    petName = "뽀삐",
                    uiState = ResultUiState.Error,
                    imageUri = Uri.EMPTY,
                    navigateToHome = {},
                    navigateToMap = {},
                    navigateToDiagnosis = {},
                )
            }
        }
        composeTestRule.onNodeWithText("다시 진단하기").assertIsDisplayed()
    }

    @Test
    fun `에러_상태에서_다시_진단하기_버튼_클릭_시_navigateToDiagnosis_콜백이_호출된다`() {
        var called = false
        composeTestRule.setContent {
            PpoPpiTheme {
                ResultScreen(
                    petName = "뽀삐",
                    uiState = ResultUiState.Error,
                    imageUri = Uri.EMPTY,
                    navigateToHome = {},
                    navigateToMap = {},
                    navigateToDiagnosis = { called = true },
                )
            }
        }
        composeTestRule.onNodeWithText("다시 진단하기").performClick()
        composeTestRule.runOnIdle { assertTrue(called) }
    }

    // 성공 상태 - 긴급 (URGENT)

    @Test
    fun `URGENT_진단_결과에서_질병명이_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                ResultScreen(
                    petName = "뽀삐",
                    uiState = ResultUiState.Success(createDiagnosis(Triage.URGENT)),
                    imageUri = Uri.EMPTY,
                    navigateToHome = {},
                    navigateToMap = {},
                    navigateToDiagnosis = {},
                )
            }
        }
        composeTestRule.onNodeWithText("사진 분석 결과 | 백내장 | 왼쪽 눈").assertIsDisplayed()
    }

    @Test
    fun `URGENT_진단_결과에서_주변_동물병원_찾아보기_버튼이_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                ResultScreen(
                    petName = "뽀삐",
                    uiState = ResultUiState.Success(createDiagnosis(Triage.URGENT)),
                    imageUri = Uri.EMPTY,
                    navigateToHome = {},
                    navigateToMap = {},
                    navigateToDiagnosis = {},
                )
            }
        }
        composeTestRule.onNodeWithText("주변 동물 병원 찾아 보기").performScrollTo().assertIsDisplayed()
    }

    @Test
    fun `URGENT_진단_결과에서_주변_동물병원_버튼_클릭_시_navigateToMap_콜백이_호출된다`() {
        var called = false
        composeTestRule.setContent {
            PpoPpiTheme {
                ResultScreen(
                    petName = "뽀삐",
                    uiState = ResultUiState.Success(createDiagnosis(Triage.URGENT)),
                    imageUri = Uri.EMPTY,
                    navigateToHome = {},
                    navigateToMap = { called = true },
                    navigateToDiagnosis = {},
                )
            }
        }
        composeTestRule.onNodeWithText("주변 동물 병원 찾아 보기").performScrollTo().performClick()
        composeTestRule.runOnIdle { assertTrue(called) }
    }

    @Test
    fun `URGENT_진단_결과에서_다시_진단하기_링크_클릭_시_navigateToDiagnosis_콜백이_호출된다`() {
        var called = false
        composeTestRule.setContent {
            PpoPpiTheme {
                ResultScreen(
                    petName = "뽀삐",
                    uiState = ResultUiState.Success(createDiagnosis(Triage.URGENT)),
                    imageUri = Uri.EMPTY,
                    navigateToHome = {},
                    navigateToMap = {},
                    navigateToDiagnosis = { called = true },
                )
            }
        }
        composeTestRule.onNodeWithText("다시 진단하기").performScrollTo().performClick()
        composeTestRule.runOnIdle { assertTrue(called) }
    }

    @Test
    fun `AI_선별_결과_면책_문구가_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                ResultScreen(
                    petName = "뽀삐",
                    uiState = ResultUiState.Success(createDiagnosis(Triage.NORMAL)),
                    imageUri = Uri.EMPTY,
                    navigateToHome = {},
                    navigateToMap = {},
                    navigateToDiagnosis = {},
                )
            }
        }
        composeTestRule.onNodeWithText(
            "AI 선별 결과이며 확진이 아닙니다\n최종 판단은 수의사에게 있습니다",
        ).performScrollTo().assertIsDisplayed()
    }

    // 성공 상태 - 정상 (NORMAL)

    @Test
    fun `NORMAL_진단_결과에서_완료_버튼이_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                ResultScreen(
                    petName = "뽀삐",
                    uiState = ResultUiState.Success(createDiagnosis(Triage.NORMAL)),
                    imageUri = Uri.EMPTY,
                    navigateToHome = {},
                    navigateToMap = {},
                    navigateToDiagnosis = {},
                )
            }
        }
        composeTestRule.onNodeWithText("완료").performScrollTo().assertIsDisplayed()
    }

    @Test
    fun `NORMAL_진단_결과에서_완료_버튼_클릭_시_navigateToHome_콜백이_호출된다`() {
        var called = false
        composeTestRule.setContent {
            PpoPpiTheme {
                ResultScreen(
                    petName = "뽀삐",
                    uiState = ResultUiState.Success(createDiagnosis(Triage.NORMAL)),
                    imageUri = Uri.EMPTY,
                    navigateToHome = { called = true },
                    navigateToMap = {},
                    navigateToDiagnosis = {},
                )
            }
        }
        composeTestRule.onNodeWithText("완료").performScrollTo().performClick()
        composeTestRule.runOnIdle { assertTrue(called) }
    }
}
