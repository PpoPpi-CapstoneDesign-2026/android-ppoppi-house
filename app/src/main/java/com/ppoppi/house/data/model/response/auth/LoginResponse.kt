package com.ppoppi.house.data.model.response.auth

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("memberId")
    val memberId: Long,
    @SerializedName("isOnboarding")
    val isOnboarding: Boolean,
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("refresh_token")
    val refreshToken: String,
)
