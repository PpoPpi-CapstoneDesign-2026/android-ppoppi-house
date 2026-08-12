package com.ppoppi.house.domain.repository

import com.ppoppi.house.domain.model.Pet
import com.ppoppi.house.domain.model.SPECIES

interface PetsRepository {
    suspend fun getBreeds(species: SPECIES): List<String>

    suspend fun postPet(pet: Pet): Result<Unit>

    suspend fun getPets(): List<Pet>

    suspend fun deletePet(petId: Long): Result<Unit>

    suspend fun putPet(
        petId: Long,
        pet: Pet,
    ): Result<Unit>
}
