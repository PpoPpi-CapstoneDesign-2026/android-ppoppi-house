package com.ppoppi.house.data.datasource

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class TokenDataSourceImpl
    @Inject
    constructor(
        @ApplicationContext context: Context,
    ) : TokenDataSource {
        private val prefs: SharedPreferences =
            context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

        override fun getAccessToken(): String? = prefs.getString(KEY_ACCESS_TOKEN, null)

        override fun setAccessToken(token: String) {
            prefs.edit().putString(KEY_ACCESS_TOKEN, token).apply()
        }

        override fun deleteAccessToken() {
            prefs.edit().remove(KEY_ACCESS_TOKEN).apply()
        }

        override fun getRefreshToken(): String? = prefs.getString(KEY_REFRESH_TOKEN, null)

        override fun setRefreshToken(token: String) {
            prefs.edit().putString(KEY_REFRESH_TOKEN, token).apply()
        }

        override fun deleteRefreshToken() {
            prefs.edit().remove(KEY_REFRESH_TOKEN).apply()
        }

        companion object {
            private const val PREFS_NAME = "auth_prefs"
            private const val KEY_ACCESS_TOKEN = "access_token"
            private const val KEY_REFRESH_TOKEN = "refresh_token"
        }
    }
