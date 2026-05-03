package com.ppoppi.house.data.network

import com.ppoppi.house.BuildConfig
import com.ppoppi.house.data.datasource.TokenDataSource
import com.ppoppi.house.data.model.error.ResponseError
import com.ppoppi.house.di.InternalOkHttpClient
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import org.json.JSONObject
import java.net.HttpURLConnection
import javax.inject.Inject

class AuthorizationInterceptor
    @Inject
    constructor(
        private val tokenDataSource: TokenDataSource,
        @InternalOkHttpClient private val internalClient: OkHttpClient,
    ) : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            // 1. 최초 요청
            val initialRequest =
                chain
                    .request()
                    .newBuilder()
                    .addHeader(
                        HEADER_NAME_AUTHORIZATION,
                        HEADER_VALUE_AUTHORIZATION.format(tokenDataSource.getAccessToken()),
                    ).build()

            val response = chain.proceed(initialRequest)

            // 2. 401 처리
            if (response.code != HttpURLConnection.HTTP_UNAUTHORIZED ||
                !hasAccessTokenExpiredError(response)
            ) {
                return response
            }

            response.close()

            // 3. 토큰 재발급
            val newTokens = reissueToken() ?: return chain.proceed(initialRequest)

            tokenDataSource.setAccessToken(newTokens.accessToken)
            tokenDataSource.setRefreshToken(newTokens.refreshToken)

            // 4. 새로운 토큰으로 재시도
            val newRequest =
                chain
                    .request()
                    .newBuilder()
                    .header(
                        HEADER_NAME_AUTHORIZATION,
                        HEADER_VALUE_AUTHORIZATION.format(newTokens.accessToken),
                    ).build()

            return chain.proceed(newRequest)
        }

        private fun reissueToken(): NewTokens? {
            val refreshToken = tokenDataSource.getRefreshToken() ?: return null

            val requestBody =
                JSONObject
                    .quote(refreshToken)
                    .toRequestBody("application/json; charset=utf-8".toMediaType())

            val request =
                Request
                    .Builder()
                    .url("${BuildConfig.BASE_URL.trimEnd('/')}/api/auth/reissue")
                    .post(requestBody)
                    .build()

            return runCatching {
                internalClient.newCall(request).execute().use { response ->
                    if (!response.isSuccessful) return null
                    val json = JSONObject(response.body.string())
                    NewTokens(
                        accessToken = json.getString("accessToken"),
                        refreshToken = json.getString("refreshToken"),
                    )
                }
            }.getOrNull()
        }

        private fun hasAccessTokenExpiredError(response: Response): Boolean =
            runCatching {
                val rawBody = response.body.string()
                val json = JSONObject(rawBody)
                json.optString(ERROR_BODY) == ResponseError.AccountError.Unauthorized.code
            }.getOrElse { false }
                .also { response.close() }

        private data class NewTokens(
            val accessToken: String,
            val refreshToken: String,
        )

        companion object {
            private const val HEADER_NAME_AUTHORIZATION = "Authorization"
            private const val HEADER_VALUE_AUTHORIZATION = "Bearer %s"
            private const val ERROR_BODY = "error"
        }
    }
