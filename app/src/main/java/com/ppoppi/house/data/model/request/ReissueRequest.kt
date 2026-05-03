package com.ppoppi.house.data.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReissueRequest(
    @SerialName("refreshToken")
    val refreshToken: String,
)
