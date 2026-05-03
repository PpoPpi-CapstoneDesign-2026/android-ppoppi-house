package com.ppoppi.house.data.model.error

sealed class ResponseError {
    sealed class AccountError(val code: String) : ResponseError() {
        data object Unauthorized : AccountError("TOKEN_EXPIRED")
    }
}
