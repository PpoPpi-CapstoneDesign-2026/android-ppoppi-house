package com.ppoppi.house.data.model.response.pet

import com.ppoppi.house.domain.model.COLOR
import com.ppoppi.house.domain.model.Pet
import com.ppoppi.house.domain.model.SEX
import com.ppoppi.house.domain.model.SEX.Companion.from
import com.ppoppi.house.domain.model.SPECIES
import com.ppoppi.house.domain.model.SPECIES.Companion.from
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class PetsResponse : ArrayList<PetsResponse.PetResponse>() {
    @Serializable
    data class PetResponse(
        @SerialName("petId")
        val petId: Long,
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
        val color: Int,
    )
}

fun PetsResponse.PetResponse.toDomain(): Pet =
    Pet(
        name = this.name,
        species = SPECIES.from(this.species),
        breed = this.breed,
        age = this.age,
        sex = SEX.from(this.sex),
        color = COLOR.entries[this.color],
        id = this.petId,
    )
