package com.sparkfusion.core.hilt_core

import android.content.SharedPreferences
import com.sparkfusion.core.common.shared_preferences.ACCESS_TOKEN_KEY
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthInterceptor @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : Interceptor {

    private val pathsWithAuth: Set<String> = HashSet(
        mutableListOf(
            "/auth/check-token",
            "/education/institution/ofAdmin",
            "/admin/account/search",
            "/admin/all"
        )
    )

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val builder = originalRequest.newBuilder()

        val path: String = originalRequest.url.encodedPath
        if (pathsWithAuth.contains(path)) {
            val token = sharedPreferences.getString(ACCESS_TOKEN_KEY, null)
            token.let { builder.addHeader("Authorization", "Bearer $it") }
        }

        val requestWithToken = builder
            .url(originalRequest.url)
            .build()

        return chain.proceed(requestWithToken)
    }
}





























