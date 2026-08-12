package com.ppoppi.house.ui.onboarding.list

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ppoppi.house.domain.model.COLOR
import com.ppoppi.house.domain.model.Pet
import com.ppoppi.house.domain.model.SEX
import com.ppoppi.house.domain.model.SPECIES
import com.ppoppi.house.ui.theme.PpoPpiTheme
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PetListScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val dog =
        Pet(
            id = 1L,
            name = "뽀삐",
            species = SPECIES.DOG,
            breed = "말티즈",
            age = 2,
            sex = SEX.MALE,
            color = COLOR.PRIMARY100,
        )

    private val cat =
        Pet(
            id = 2L,
            name = "나비",
            species = SPECIES.CAT,
            breed = "페르시안",
            age = 3,
            sex = SEX.FEMALE,
            color = COLOR.PRIMARY200,
        )

    private val dog2 =
        Pet(
            id = 3L,
            name = "초코",
            species = SPECIES.DOG,
            breed = "푸들",
            age = 1,
            sex = SEX.MALE,
            color = COLOR.PRIMARY600,
        )

    // 화면 기본 요소

    @Test
    fun `상단_바에_내_반려동물_타이틀이_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                PetListScreen(onStart = {}, onEdit = {}, onRegister = {}, pets = emptyList())
            }
        }
        composeTestRule.onNodeWithText("내 반려동물").assertIsDisplayed()
    }

    @Test
    fun `등록된_반려동물_섹션_라벨이_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                PetListScreen(onStart = {}, onEdit = {}, onRegister = {}, pets = emptyList())
            }
        }
        composeTestRule.onNodeWithText("등록된 반려동물").assertIsDisplayed()
    }

    @Test
    fun `시작하기_버튼이_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                PetListScreen(onStart = {}, onEdit = {}, onRegister = {}, pets = emptyList())
            }
        }
        composeTestRule.onNodeWithText("시작하기").assertIsDisplayed()
    }

    // 반려동물 추가 버튼 표시 여부

    @Test
    fun `반려동물이_없을_때_다른_반려동물_추가_버튼이_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                PetListScreen(onStart = {}, onEdit = {}, onRegister = {}, pets = emptyList())
            }
        }
        composeTestRule.onNodeWithText("다른 반려동물 추가").assertIsDisplayed()
    }

    @Test
    fun `반려동물이_2마리일_때_다른_반려동물_추가_버튼이_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                PetListScreen(
                    onStart = {},
                    onEdit = {},
                    onRegister = {},
                    pets = listOf(dog, cat),
                )
            }
        }
        composeTestRule.onNodeWithText("다른 반려동물 추가").assertIsDisplayed()
    }

    @Test
    fun `반려동물이_3마리일_때_다른_반려동물_추가_버튼이_표시되지_않는다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                PetListScreen(
                    onStart = {},
                    onEdit = {},
                    onRegister = {},
                    pets = listOf(dog, cat, dog2),
                )
            }
        }
        val nodes = composeTestRule.onAllNodesWithText("다른 반려동물 추가").fetchSemanticsNodes()
        assert(nodes.isEmpty())
    }

    // 반려동물 목록 표시

    @Test
    fun `등록된_반려동물_이름이_목록에_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                PetListScreen(
                    onStart = {},
                    onEdit = {},
                    onRegister = {},
                    pets = listOf(dog),
                )
            }
        }
        composeTestRule.onNodeWithText("뽀삐").assertIsDisplayed()
    }

    @Test
    fun `여러_반려동물의_이름이_모두_목록에_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                PetListScreen(
                    onStart = {},
                    onEdit = {},
                    onRegister = {},
                    pets = listOf(dog, cat),
                )
            }
        }
        composeTestRule.onNodeWithText("뽀삐").assertIsDisplayed()
        composeTestRule.onNodeWithText("나비").assertIsDisplayed()
    }

    // 사용자 인터랙션

    @Test
    fun `시작하기_버튼_클릭_시_onStart_콜백이_호출된다`() {
        var onStartCalled = false
        composeTestRule.setContent {
            PpoPpiTheme {
                PetListScreen(
                    onStart = { onStartCalled = true },
                    onEdit = {},
                    onRegister = {},
                    pets = emptyList(),
                )
            }
        }
        composeTestRule.onNodeWithText("시작하기").performClick()
        composeTestRule.runOnIdle { assertTrue(onStartCalled) }
    }

    @Test
    fun `다른_반려동물_추가_버튼_클릭_시_onRegister_콜백이_호출된다`() {
        var onRegisterCalled = false
        composeTestRule.setContent {
            PpoPpiTheme {
                PetListScreen(
                    onStart = {},
                    onEdit = {},
                    onRegister = { onRegisterCalled = true },
                    pets = emptyList(),
                )
            }
        }
        composeTestRule.onNodeWithText("다른 반려동물 추가").performClick()
        composeTestRule.runOnIdle { assertTrue(onRegisterCalled) }
    }

    @Test
    fun `반려동물_아이템_클릭_시_onEdit_콜백이_해당_반려동물과_함께_호출된다`() {
        var editedPet: Pet? = null
        composeTestRule.setContent {
            PpoPpiTheme {
                PetListScreen(
                    onStart = {},
                    onEdit = { editedPet = it },
                    onRegister = {},
                    pets = listOf(dog),
                )
            }
        }
        composeTestRule.onNodeWithText("뽀삐").performClick()
        composeTestRule.runOnIdle { assertEquals(dog, editedPet) }
    }
}
