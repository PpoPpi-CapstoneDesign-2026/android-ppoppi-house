package com.ppoppi.house.data.model.response.diary

import com.ppoppi.house.domain.model.HealthChecklist
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class HealthChecklistResponse : ArrayList<HealthChecklistResponse.HealthChecklistResponseItem>() {
    @Serializable
    data class HealthChecklistResponseItem(
        @SerialName("checkId")
        val checkId: Long,
        @SerialName("checkName")
        val checkName: String,
    )
}

fun HealthChecklistResponse.HealthChecklistResponseItem.toDomain(): HealthChecklist =
    HealthChecklist(
        id = checkId,
        description = checkName,
    )
