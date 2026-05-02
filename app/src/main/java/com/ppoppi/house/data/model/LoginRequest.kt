package com.ppoppi.house.data.model

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("accessToken") val accessToken: String,
)
