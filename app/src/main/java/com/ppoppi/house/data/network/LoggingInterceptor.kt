package com.ppoppi.house.data.network

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import okio.Buffer
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class LoggingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val requestBody = request.body?.let { body ->
            val buffer = Buffer()
            body.writeTo(buffer)
            buffer.readUtf8()
        } ?: "없음"

        Log.d(TAG, buildString {
            appendLine("▶ 요청")
            appendLine("URL     : ${request.url}")
            appendLine("바디    : $requestBody")
            append("시간    : ${now()}")
        })

        val startTime = System.currentTimeMillis()

        val response = try {
            chain.proceed(request)
        } catch (e: Exception) {
            Log.d(TAG, buildString {
                appendLine("◀ 네트워크 오류")
                appendLine("URL     : ${request.url}")
                append("오류    : ${e.message}")
            })
            throw e
        }

        val elapsedTime = System.currentTimeMillis() - startTime
        val responseBody = response.peekBody(Long.MAX_VALUE).string()

        if (response.isSuccessful) {
            Log.d(TAG, buildString {
                appendLine("◀ 응답")
                appendLine("URL     : ${response.request.url}")
                appendLine("바디    : $responseBody")
                append("소요시간: ${elapsedTime}ms")
            })
        } else {
            Log.d(TAG, buildString {
                appendLine("◀ 오류 응답")
                appendLine("URL     : ${response.request.url}")
                appendLine("바디    : $responseBody")
                appendLine("소요시간: ${elapsedTime}ms")
                appendLine("에러코드: ${response.code}")
                append("에러메시지: ${response.message}")
            })
        }

        return response
    }

    private fun now(): String =
        SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.getDefault()).format(Date())

    companion object {
        private const val TAG = "Retrofit"
    }
}
