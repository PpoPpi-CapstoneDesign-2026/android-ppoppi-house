package com.ppoppi.house.data.repository

import com.ppoppi.house.data.model.request.pet.PetRegisterRequest
import com.ppoppi.house.data.model.response.pet.toDomain
import com.ppoppi.house.data.service.PetService
import com.ppoppi.house.domain.model.Pet
import com.ppoppi.house.domain.model.SPECIES
import com.ppoppi.house.domain.repository.PetsRepository
import javax.inject.Inject

class PetsRepositoryImpl
    @Inject
    constructor(
        private val petService: PetService,
    ) : PetsRepository {
        override suspend fun getBreeds(species: SPECIES): List<String> = petService.getBreeds(species.name).toList()

        override suspend fun postPet(pet: Pet): Result<Unit> =
            runCatching {
                petService.postPets(
                    PetRegisterRequest(
                        name = pet.name,
                        species = pet.species.name,
                        breed = pet.breed,
                        age = pet.age,
                        sex = pet.sex.name,
                        color = pet.color.ordinal,
                    ),
                )
            }

        override suspend fun getPets(): List<Pet> = petService.getPets().map { it.toDomain() }
    }
