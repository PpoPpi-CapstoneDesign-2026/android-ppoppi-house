package com.ppoppi.house.ui.main.diary

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ppoppi.house.domain.model.COLOR
import com.ppoppi.house.domain.model.HealthChecklist
import com.ppoppi.house.domain.model.Pet
import com.ppoppi.house.domain.model.SEX
import com.ppoppi.house.domain.model.SPECIES
import com.ppoppi.house.ui.main.diary.component.DiaryItem
import com.ppoppi.house.ui.theme.PpoPpiTheme
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DiaryItemTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val 강아지 =
        Pet(
            id = 1L,
            name = "뽀삐",
            species = SPECIES.DOG,
            breed = "말티즈",
            age = 2,
            sex = SEX.MALE,
            color = COLOR.PRIMARY100,
        )

    @Test
    fun `반려동물_이름이_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                DiaryItem(
                    pet = 강아지,
                    diagnosis = null,
                    memo = null,
                    healthChecklist = emptyList(),
                )
            }
        }
        composeTestRule.onNodeWithText("뽀삐").assertIsDisplayed()
    }

    @Test
    fun `메모_텍스트가_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                DiaryItem(
                    pet = 강아지,
                    diagnosis = null,
                    memo = "오늘 산책을 다녀왔어요",
                    healthChecklist = emptyList(),
                )
            }
        }
        composeTestRule.onNodeWithText("오늘 산책을 다녀왔어요").assertIsDisplayed()
    }

    @Test
    fun `메모가_있을_때_메모_섹션_헤더가_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                DiaryItem(
                    pet = 강아지,
                    diagnosis = null,
                    memo = "메모 내용",
                    healthChecklist = emptyList(),
                )
            }
        }
        composeTestRule.onNodeWithText("📝메모").assertIsDisplayed()
    }

    @Test
    fun `메모가_null이면_메모_섹션_헤더가_표시되지_않는다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                DiaryItem(
                    pet = 강아지,
                    diagnosis = null,
                    memo = null,
                    healthChecklist = emptyList(),
                )
            }
        }
        val nodes = composeTestRule.onAllNodesWithText("📝메모").fetchSemanticsNodes()
        assertTrue(nodes.isEmpty())
    }

    @Test
    fun `메모가_빈_문자열이면_메모_섹션_헤더가_표시되지_않는다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                DiaryItem(
                    pet = 강아지,
                    diagnosis = null,
                    memo = "",
                    healthChecklist = emptyList(),
                )
            }
        }
        val nodes = composeTestRule.onAllNodesWithText("📝메모").fetchSemanticsNodes()
        assertTrue(nodes.isEmpty())
    }

    @Test
    fun `건강_체크리스트_아이템이_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                DiaryItem(
                    pet = 강아지,
                    diagnosis = null,
                    memo = null,
                    healthChecklist =
                        listOf(
                            HealthChecklist(id = 1L, description = "식욕 없음"),
                            HealthChecklist(id = 2L, description = "활동량 적음"),
                        ),
                )
            }
        }
        composeTestRule.onNodeWithText("식욕 없음").assertIsDisplayed()
        composeTestRule.onNodeWithText("활동량 적음").assertIsDisplayed()
    }

    @Test
    fun `진단_결과가_null이면_진단_결과_섹션이_표시되지_않는다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                DiaryItem(
                    pet = 강아지,
                    diagnosis = null,
                    memo = null,
                    healthChecklist = emptyList(),
                )
            }
        }
        val nodes = composeTestRule.onAllNodesWithText("🔍진단 결과").fetchSemanticsNodes()
        assertTrue(nodes.isEmpty())
    }
}
