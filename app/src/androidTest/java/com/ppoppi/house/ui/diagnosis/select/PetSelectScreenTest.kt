package com.ppoppi.house.ui.diagnosis.select

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
class PetSelectScreenTest {
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

    private val 고양이 =
        Pet(
            id = 2L,
            name = "나비",
            species = SPECIES.CAT,
            breed = "페르시안",
            age = 3,
            sex = SEX.FEMALE,
            color = COLOR.PRIMARY200,
        )

    @Test
    fun `반려동물_선택_힌트_텍스트가_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                PetSelectScreen(onBackClick = {}, onSelect = {}, pets = listOf(강아지))
            }
        }
        composeTestRule.onNodeWithText("어떤 반려동물의 안구를 진단할지 선택해 주세요").assertIsDisplayed()
    }

    @Test
    fun `반려동물_목록에_이름이_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                PetSelectScreen(onBackClick = {}, onSelect = {}, pets = listOf(강아지))
            }
        }
        composeTestRule.onNodeWithText("뽀삐").assertIsDisplayed()
    }

    @Test
    fun `여러_반려동물의_이름이_모두_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                PetSelectScreen(onBackClick = {}, onSelect = {}, pets = listOf(강아지, 고양이))
            }
        }
        composeTestRule.onNodeWithText("뽀삐").assertIsDisplayed()
        composeTestRule.onNodeWithText("나비").assertIsDisplayed()
    }

    @Test
    fun `반려동물_아이템_클릭_시_onSelect_콜백이_해당_반려동물과_함께_호출된다`() {
        var selectedPet: Pet? = null
        composeTestRule.setContent {
            PpoPpiTheme {
                PetSelectScreen(
                    onBackClick = {},
                    onSelect = { selectedPet = it },
                    pets = listOf(강아지),
                )
            }
        }
        composeTestRule.onNodeWithText("뽀삐").performClick()
        composeTestRule.runOnIdle { assertEquals(강아지, selectedPet) }
    }

    @Test
    fun `반려동물이_없으면_힌트_텍스트만_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                PetSelectScreen(onBackClick = {}, onSelect = {}, pets = emptyList())
            }
        }
        composeTestRule.onNodeWithText("어떤 반려동물의 안구를 진단할지 선택해 주세요").assertIsDisplayed()
        val nodes = composeTestRule.onAllNodesWithText("뽀삐").fetchSemanticsNodes()
        assertTrue(nodes.isEmpty())
    }
}
