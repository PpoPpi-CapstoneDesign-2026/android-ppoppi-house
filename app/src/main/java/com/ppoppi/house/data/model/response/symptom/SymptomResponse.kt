package com.ppoppi.house.data.model.response.symptom

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class SymptomResponse : ArrayList<SymptomResponse.SymptomResponseItem>() {
    @Serializable
    data class SymptomResponseItem(
        @SerialName("id")
        val id: Long,
        @SerialName("description")
        val description: String,
    )
}
