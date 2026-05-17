package com.ppoppi.house.data.model.response.diary


import com.ppoppi.house.data.model.response.diagnosis.DiagnosisResponse
import com.ppoppi.house.data.model.response.diagnosis.toDomain
import com.ppoppi.house.data.model.response.diary.DailyDiariesResponse
import com.ppoppi.house.domain.model.Diary
import com.ppoppi.house.domain.model.HealthChecklist
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class DailyDiariesResponse : ArrayList<DailyDiariesResponse.DailyDiariesResponsesItem>(){
    @Serializable
    data class DailyDiariesResponsesItem(
        @SerialName("diaryId")
        val diaryId: Long,
        @SerialName("petId")
        val petId: Long,
        @SerialName("petName")
        val petName: String,
        @SerialName("entryDate")
        val entryDate: String,
        @SerialName("memo")
        val memo: String? = null,
        @SerialName("checkIds")
        val checkIds: List<Long>,
        @SerialName("checkNames")
        val checkNames: List<String>,
        @SerialName("diagnosis")
        val diagnosis: DiagnosisResponse
    )
}

fun DailyDiariesResponse.DailyDiariesResponsesItem.toDomain(): Diary =
    Diary(
        id = this.diaryId,
        petId = this.petId,
        petName = this.petName,
        diagnosis = this.diagnosis.toDomain(),
        memo = this.memo ?: "",
        healthChecklist =
            this.checkIds.zip(this.checkNames).map { (id, name) ->
                HealthChecklist(id, name)
            },
    )
