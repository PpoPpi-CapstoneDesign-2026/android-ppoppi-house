package com.ppoppi.house.data.datasource

interface TokenDataSource {
    fun getAccessToken(): String?

    fun setAccessToken(token: String)

    fun deleteAccessToken()

    fun getRefreshToken(): String?

    fun setRefreshToken(token: String)

    fun deleteRefreshToken()
}
