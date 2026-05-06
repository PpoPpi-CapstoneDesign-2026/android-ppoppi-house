package com.ppoppi.house.data.model.response.diary

import com.ppoppi.house.data.model.response.diagnosis.DiagnosisResponse
import com.ppoppi.house.data.model.response.diagnosis.toDomain
import com.ppoppi.house.domain.model.Diary
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class DailyDiariesResponse : ArrayList<DailyDiariesResponse.DailyDiaryResponseItem>() {
    @Serializable
    data class DailyDiaryResponseItem(
        @SerialName("diaryId")
        val diaryId: Long,
        @SerialName("petId")
        val petId: Long,
        @SerialName("petName")
        val petName: String,
        @SerialName("entryDate")
        val entryDate: String,
        @SerialName("memo")
        val memo: String,
        @SerialName("checkIds")
        val checkIds: List<Int>,
        @SerialName("checkNames")
        val checkNames: List<String>,
        @SerialName("diagnosis")
        val diagnosis: DiagnosisResponse? = null,
    )
}

fun DailyDiariesResponse.DailyDiaryResponseItem.toDomain(): Diary =
    Diary(
        id = this.diaryId,
        petId = this.petId,
        petName = this.petName,
        diagnosis = this.diagnosis?.toDomain(),
        memo = this.memo,
        healthChecklist = this.checkNames,
    )
