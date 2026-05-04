package com.ppoppi.house.data.service

import com.ppoppi.house.data.model.request.pet.PetRegisterRequest
import com.ppoppi.house.data.model.response.pet.BreedsResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface PetService {
    @GET("/pets/breeds")
    suspend fun getBreeds(
        @Query("species") species: String,
    ): BreedsResponse

    @POST("/pets")
    suspend fun postPets(
        @Body petRegisterRequest: PetRegisterRequest
    )
}
