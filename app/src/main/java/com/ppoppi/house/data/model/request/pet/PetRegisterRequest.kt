package com.ppoppi.house.data.model.request.pet


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PetRegisterRequest(
    @SerialName("name")
    val name: String,
    @SerialName("species")
    val species: String,
    @SerialName("breed")
    val breed: String,
    @SerialName("age")
    val age: Int,
    @SerialName("sex")
    val sex: String,
    @SerialName("color")
    val color: Int
)
