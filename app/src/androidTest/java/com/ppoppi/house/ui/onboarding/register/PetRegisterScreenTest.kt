package com.ppoppi.house.ui.onboarding.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ppoppi.house.domain.model.SEX
import com.ppoppi.house.domain.model.SPECIES
import com.ppoppi.house.ui.onboarding.register.component.AgeInputSection
import com.ppoppi.house.ui.onboarding.register.component.BreedInputSection
import com.ppoppi.house.ui.onboarding.register.component.ColorInputSection
import com.ppoppi.house.ui.onboarding.register.component.NameInputSection
import com.ppoppi.house.ui.onboarding.register.component.SexInputSection
import com.ppoppi.house.ui.onboarding.register.component.SpeciesInputSection
import com.ppoppi.house.ui.theme.PpoPpiTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PetRegisterScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    // 이름 입력 섹션

    @Test
    fun `이름_섹션에_이름_라벨이_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                NameInputSection(name = "", onValueChanged = {})
            }
        }
        composeTestRule.onNodeWithText("이름").assertIsDisplayed()
    }

    @Test
    fun `이름_입력란에_힌트_텍스트가_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                NameInputSection(name = "", onValueChanged = {})
            }
        }
        composeTestRule.onNodeWithText("반려동물의 이름을 입력해 주세요").assertIsDisplayed()
    }

    @Test
    fun `이름이_입력되면_힌트_텍스트가_사라진다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                NameInputSection(name = "뽀삐", onValueChanged = {})
            }
        }
        val nodes = composeTestRule.onAllNodesWithText("반려동물의 이름을 입력해 주세요").fetchSemanticsNodes()
        assert(nodes.isEmpty())
    }

    @Test
    fun `이름을_입력하면_콜백이_입력된_값과_함께_호출된다`() {
        var enteredName = ""
        composeTestRule.setContent {
            PpoPpiTheme {
                NameInputSection(name = enteredName, onValueChanged = { enteredName = it })
            }
        }
        composeTestRule.onNode(hasSetTextAction()).performTextInput("뽀삐")
        composeTestRule.runOnIdle { assert(enteredName == "뽀삐") }
    }

    // 종류 입력 섹션

    @Test
    fun `종류_섹션에_종류_라벨이_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                SpeciesInputSection(species = SPECIES.DOG, onValueChanged = {})
            }
        }
        composeTestRule.onNodeWithText("종류").assertIsDisplayed()
    }

    @Test
    fun `종류_섹션에_강아지와_고양이_버튼이_모두_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                SpeciesInputSection(species = SPECIES.DOG, onValueChanged = {})
            }
        }
        composeTestRule.onNodeWithText("🐶 강아지").assertIsDisplayed()
        composeTestRule.onNodeWithText("🐱 고양이").assertIsDisplayed()
    }

    @Test
    fun `고양이를_선택하면_종류가_고양이로_변경된다`() {
        var species by mutableStateOf(SPECIES.DOG)
        composeTestRule.setContent {
            PpoPpiTheme {
                SpeciesInputSection(species = species, onValueChanged = { species = it })
            }
        }
        composeTestRule.onNodeWithText("🐱 고양이").performClick()
        composeTestRule.runOnIdle { assert(species == SPECIES.CAT) }
    }

    @Test
    fun `강아지를_선택하면_종류가_강아지로_변경된다`() {
        var species by mutableStateOf(SPECIES.CAT)
        composeTestRule.setContent {
            PpoPpiTheme {
                SpeciesInputSection(species = species, onValueChanged = { species = it })
            }
        }
        composeTestRule.onNodeWithText("🐶 강아지").performClick()
        composeTestRule.runOnIdle { assert(species == SPECIES.DOG) }
    }

    // 품종 입력 섹션

    @Test
    fun `품종_섹션에_품종_라벨이_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                BreedInputSection(breed = null, breeds = emptyList(), onValueChanged = {})
            }
        }
        composeTestRule.onNodeWithText("품종").assertIsDisplayed()
    }

    @Test
    fun `선택된_품종이_없을_때_품종_선택_힌트가_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                BreedInputSection(breed = null, breeds = listOf("말티즈", "푸들"), onValueChanged = {})
            }
        }
        composeTestRule.onNodeWithText("품종을 선택해 주세요").assertIsDisplayed()
    }

    @Test
    fun `선택된_품종이_있으면_해당_품종이_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                BreedInputSection(breed = "말티즈", breeds = listOf("말티즈", "푸들"), onValueChanged = {})
            }
        }
        composeTestRule.onNodeWithText("말티즈").assertIsDisplayed()
    }

    // 성별 입력 섹션

    @Test
    fun `성별_섹션에_성별_라벨이_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                SexInputSection(sex = SEX.MALE, onValueChanged = {})
            }
        }
        composeTestRule.onNodeWithText("성별").assertIsDisplayed()
    }

    @Test
    fun `성별_섹션에_남과_여_버튼이_모두_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                SexInputSection(sex = SEX.MALE, onValueChanged = {})
            }
        }
        composeTestRule.onNodeWithText("남").assertIsDisplayed()
        composeTestRule.onNodeWithText("여").assertIsDisplayed()
    }

    @Test
    fun `여를_선택하면_성별이_여로_변경된다`() {
        var sex by mutableStateOf(SEX.MALE)
        composeTestRule.setContent {
            PpoPpiTheme {
                SexInputSection(sex = sex, onValueChanged = { sex = it })
            }
        }
        composeTestRule.onNodeWithText("여").performClick()
        composeTestRule.runOnIdle { assert(sex == SEX.FEMALE) }
    }

    @Test
    fun `남을_선택하면_성별이_남으로_변경된다`() {
        var sex by mutableStateOf(SEX.FEMALE)
        composeTestRule.setContent {
            PpoPpiTheme {
                SexInputSection(sex = sex, onValueChanged = { sex = it })
            }
        }
        composeTestRule.onNodeWithText("남").performClick()
        composeTestRule.runOnIdle { assert(sex == SEX.MALE) }
    }

    // 나이 입력 섹션

    @Test
    fun `나이_섹션에_나이_라벨이_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                AgeInputSection(age = 1, onDecreaseAge = {}, onIncreaseAge = {})
            }
        }
        composeTestRule.onNodeWithText("나이").assertIsDisplayed()
    }

    @Test
    fun `나이_섹션에_현재_나이가_살_단위로_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                AgeInputSection(age = 3, onDecreaseAge = {}, onIncreaseAge = {})
            }
        }
        composeTestRule.onNodeWithText("3살").assertIsDisplayed()
    }

    @Test
    fun `나이_증가_버튼을_클릭하면_나이가_1_증가한다`() {
        var age by mutableStateOf(2)
        composeTestRule.setContent {
            PpoPpiTheme {
                AgeInputSection(age = age, onDecreaseAge = { age-- }, onIncreaseAge = { age++ })
            }
        }
        composeTestRule.onNodeWithContentDescription("나이 증가 버튼").performClick()
        composeTestRule.runOnIdle { assert(age == 3) }
    }

    @Test
    fun `나이_감소_버튼을_클릭하면_나이가_1_감소한다`() {
        var age by mutableStateOf(3)
        composeTestRule.setContent {
            PpoPpiTheme {
                AgeInputSection(age = age, onDecreaseAge = { age-- }, onIncreaseAge = { age++ })
            }
        }
        composeTestRule.onNodeWithContentDescription("나이 감소 버튼").performClick()
        composeTestRule.runOnIdle { assert(age == 2) }
    }

    @Test
    fun `나이가_1살일_때_감소_버튼을_클릭해도_1살_미만으로_내려가지_않는다`() {
        var age by mutableStateOf(1)
        composeTestRule.setContent {
            PpoPpiTheme {
                AgeInputSection(age = age, onDecreaseAge = { age-- }, onIncreaseAge = { age++ })
            }
        }
        composeTestRule.onNodeWithContentDescription("나이 감소 버튼").performClick()
        composeTestRule.runOnIdle { assert(age == 1) }
    }

    // 색상 입력 섹션

    @Test
    fun `색상_섹션에_색상_라벨이_표시된다`() {
        composeTestRule.setContent {
            PpoPpiTheme {
                ColorInputSection(color = 0, onValueChanged = {})
            }
        }
        composeTestRule.onNodeWithText("색상").assertIsDisplayed()
    }
}
