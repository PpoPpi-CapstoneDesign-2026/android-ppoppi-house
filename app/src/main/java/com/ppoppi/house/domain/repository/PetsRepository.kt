package com.ppoppi.house.domain.repository

import com.ppoppi.house.domain.model.SPECIES

interface PetsRepository {
    suspend fun getBreeds(species: SPECIES): List<String>
}
