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

    // PetButton

    @Test
    fun `반려동물_이름이_버튼에_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                PetButton(onClick = {}, pet = dog, isSelected = false)
            }
        }
        composeTestRule.onNodeWithText("뽀삐").assertIsDisplayed()
    }

    @Test
    fun `PetButton_클릭_시_onClick_콜백이_호출된다`() {
        var clicked = false
        composeTestRule.setContent {
            PpoPpiTheme {
                PetButton(onClick = { clicked = true }, pet = dog, isSelected = false)
            }
        }
        composeTestRule.onNodeWithText("뽀삐").performClick()
        composeTestRule.runOnIdle { assertTrue(clicked) }
    }

    @Test
    fun `dog_종인_반려동물은_dog_이모지가_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                PetButton(onClick = {}, pet = dog, isSelected = false)
            }
        }
        composeTestRule.onNodeWithText("🐶").assertIsDisplayed()
    }

    @Test
    fun `cat_종인_반려동물은_cat_이모지가_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                PetButton(onClick = {}, pet = cat, isSelected = false)
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
