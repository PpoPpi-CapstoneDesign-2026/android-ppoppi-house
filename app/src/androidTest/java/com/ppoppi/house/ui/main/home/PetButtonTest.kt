package com.ppoppi.house.ui.main.home

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ppoppi.house.domain.model.COLOR
import com.ppoppi.house.domain.model.Pet
import com.ppoppi.house.domain.model.SEX
import com.ppoppi.house.domain.model.SPECIES
import com.ppoppi.house.ui.main.home.component.PetAddButton
import com.ppoppi.house.ui.main.home.component.PetButton
import com.ppoppi.house.ui.theme.PpoPpiTheme
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PetButtonTest {
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

    // PetButton

    @Test
    fun `반려동물_이름이_버튼에_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                PetButton(onClick = {}, pet = 강아지, isSelected = false)
            }
        }
        composeTestRule.onNodeWithText("뽀삐").assertIsDisplayed()
    }

    @Test
    fun `PetButton_클릭_시_onClick_콜백이_호출된다`() {
        var clicked = false
        composeTestRule.setContent {
            PpoPpiTheme {
                PetButton(onClick = { clicked = true }, pet = 강아지, isSelected = false)
            }
        }
        composeTestRule.onNodeWithText("뽀삐").performClick()
        composeTestRule.runOnIdle { assertTrue(clicked) }
    }

    @Test
    fun `강아지_종인_반려동물은_강아지_이모지가_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                PetButton(onClick = {}, pet = 강아지, isSelected = false)
            }
        }
        composeTestRule.onNodeWithText("🐶").assertIsDisplayed()
    }

    @Test
    fun `고양이_종인_반려동물은_고양이_이모지가_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                PetButton(onClick = {}, pet = 고양이, isSelected = false)
            }
        }
        composeTestRule.onNodeWithText("🐱").assertIsDisplayed()
    }

    // PetAddButton

    @Test
    fun `추가_버튼에_추가_텍스트가_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                PetAddButton(onClick = {})
            }
        }
        composeTestRule.onNodeWithText("추가").assertIsDisplayed()
    }

    @Test
    fun `PetAddButton_클릭_시_onClick_콜백이_호출된다`() {
        var clicked = false
        composeTestRule.setContent {
            PpoPpiTheme {
                PetAddButton(onClick = { clicked = true })
            }
        }
        composeTestRule.onNodeWithText("추가").performClick()
        composeTestRule.runOnIdle { assertTrue(clicked) }
    }
}
