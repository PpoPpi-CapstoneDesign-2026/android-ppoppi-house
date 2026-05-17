package com.ppoppi.house.data.model.request.diary


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DiaryRequest(
    @SerialName("petId")
    val petId: Long,
    @SerialName("memo")
    val memo: String,
    @SerialName("checkIds")
    val checkIds: List<Long>
)
