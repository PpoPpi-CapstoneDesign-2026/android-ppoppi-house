package com.ppoppi.house.data.model.request

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("accessToken") val accessToken: String,
)
