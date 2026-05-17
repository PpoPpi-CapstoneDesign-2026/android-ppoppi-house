package com.ppoppi.house.data.model.request.diary


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateDairyRequest(
    @SerialName("diaryId")
    val diaryId: Long,
    @SerialName("memo")
    val memo: String,
    @SerialName("checkIds")
    val checkIds: List<Int>
)
