package com.ppoppi.house.ui.main.diary

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ppoppi.house.domain.model.HealthChecklist
import com.ppoppi.house.ui.main.diary.component.DiaryDetailBottomSheet
import com.ppoppi.house.ui.theme.PpoPpiTheme
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DiaryDetailBottomSheetTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val 체크리스트 = listOf(
        HealthChecklist(id = 1L, description = "식욕 없음"),
        HealthChecklist(id = 2L, description = "활동량 적음"),
    )

    private fun render(
        petName: String = "뽀삐",
        memo: String = "",
        healthChecklist: List<HealthChecklist> = emptyList(),
        checkedChecklist: List<HealthChecklist> = emptyList(),
        onDismiss: () -> Unit = {},
        onUpdate: (String, List<Int>) -> Unit = { _, _ -> },
        onDelete: () -> Unit = {},
    ) {
        composeTestRule.setContent {
            PpoPpiTheme {
                DiaryDetailBottomSheet(
                    petName = petName,
                    memo = memo,
                    healthChecklist = healthChecklist,
                    checkedChecklist = checkedChecklist,
                    petId = 1L,
                    onDismiss = onDismiss,
                    onUpdate = onUpdate,
                    onDelete = onDelete,
                )
            }
        }
    }

    @Test
    fun `반려동물_이름이_포함된_수정_제목이_표시된다`() {
        render(petName = "뽀삐")
        composeTestRule.onNodeWithText("뽀삐 다이어리 수정").assertIsDisplayed()
    }

    @Test
    fun `메모_섹션_레이블이_표시된다`() {
        render()
        composeTestRule.onNodeWithText("메모").assertIsDisplayed()
    }

    @Test
    fun `건강_체크리스트_섹션_레이블이_표시된다`() {
        render()
        composeTestRule.onNodeWithText("건강 체크리스트").assertIsDisplayed()
    }

    @Test
    fun `수정하기_버튼이_표시된다`() {
        render()
        composeTestRule.onNodeWithText("수정하기").assertIsDisplayed()
    }

    @Test
    fun `삭제하기_텍스트가_표시된다`() {
        render()
        composeTestRule.onNodeWithText("삭제하기").assertIsDisplayed()
    }

    @Test
    fun `체크리스트_아이템이_표시된다`() {
        render(healthChecklist = 체크리스트)
        composeTestRule.onNodeWithText("식욕 없음").assertIsDisplayed()
        composeTestRule.onNodeWithText("활동량 적음").assertIsDisplayed()
    }

    @Test
    fun `수정하기_버튼_클릭_시_onUpdate_콜백이_호출된다`() {
        var called = false
        render(onUpdate = { _, _ -> called = true })
        composeTestRule.onNodeWithText("수정하기").performClick()
        composeTestRule.runOnIdle { assertTrue(called) }
    }

    @Test
    fun `삭제하기_클릭_시_onDelete_콜백이_호출된다`() {
        var called = false
        render(onDelete = { called = true })
        composeTestRule.onNodeWithText("삭제하기").performClick()
        composeTestRule.runOnIdle { assertTrue(called) }
    }
}
