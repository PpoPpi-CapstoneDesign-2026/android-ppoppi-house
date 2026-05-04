package com.ppoppi.house.data.repository

import com.ppoppi.house.data.service.PetService
import com.ppoppi.house.domain.model.SPECIES
import com.ppoppi.house.domain.repository.PetsRepository
import javax.inject.Inject

class PetsRepositoryImpl
    @Inject
    constructor(
        private val petService: PetService,
    ) : PetsRepository {
        override suspend fun getBreeds(species: SPECIES): List<String> = petService.getBreeds(species.name).toList()
    }
