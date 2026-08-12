package com.ppoppi.house.data.model.response.diary

import com.ppoppi.house.domain.model.COLOR
import com.ppoppi.house.domain.model.MonthDiary
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
data class MonthDiariesResponse(
    @SerialName("diaryList")
    val diaryList: List<MonthDiaryResponse>,
) {
    @Serializable
    data class MonthDiaryResponse(
        @SerialName("date")
        val date: String,
        @SerialName("tags")
        val tags: List<Int>,
    )
}

fun MonthDiariesResponse.MonthDiaryResponse.toDomain(): MonthDiary =
    MonthDiary(
        date = LocalDate.parse(this.date),
        color = this.tags.map { COLOR.entries[it] },
    )
