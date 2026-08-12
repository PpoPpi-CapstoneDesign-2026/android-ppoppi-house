package com.ppoppi.house.data.model.response.disease

import com.ppoppi.house.domain.model.Disease
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class DiseaseResponse : ArrayList<DiseaseResponse.DiseaseResponseItem>() {
    @Serializable
    data class DiseaseResponseItem(
        @SerialName("geneticDiseaseId")
        val geneticDiseaseId: Long,
        @SerialName("breed")
        val breed: String,
        @SerialName("diseaseName")
        val diseaseName: String,
        @SerialName("description")
        val description: String,
    )
}

fun DiseaseResponse.DiseaseResponseItem.toDomain(): Disease =
    Disease(
        diseaseId = geneticDiseaseId,
        name = diseaseName,
        breed = breed,
        description = description,
    )
