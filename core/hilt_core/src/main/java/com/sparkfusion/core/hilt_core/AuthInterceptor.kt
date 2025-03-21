package com.sparkfusion.core.hilt_core

import android.content.SharedPreferences
import com.sparkfusion.core.common.shared_preferences.ADMIN_ACCESS_TOKEN_KEY
import com.sparkfusion.core.common.shared_preferences.PUPIL_ACCESS_TOKEN_KEY
import com.sparkfusion.core.common.user_type.CurrentUserTypeHolder
import com.sparkfusion.core.common.user_type.UserType
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class AuthInterceptor @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : Interceptor {

    private val pathsPartsWithAuth: Set<String> = setOf(
        "/auth/check-token",
        "/education/institution/ofAdmin",
        "/admin/account/search",
        "/admin/all",
        "/admin/account/update/access",
        "/admin/account/delete",
        "/admin/icon/change",
        "/education/event/delete/",
        "/education/event/create",
        "/education/event/update",
        "/notifications/admin/update",
        "/notifications/admin",
        "/admin/notConfirmed",
        "/admin/confirm",
        "/pupil/notConfirmed",
        "/pupil/confirm/",
        "/education/about/update",
        "/education/about",
        "/education/faculty/all",
        "/education/speciality/byFaculty",
        "/education/group/all",
        "/education/group/create",
        "/education/group/update",
        "/education/group/",
        "/education/group/member/all",
        "/education/group/member/",
        "/education/group/member/prefect/",
        "/education/group/byNamePart",
        "/education/exam",
        "/education/exam/create",
        "/education/exam/update",
        "/education/schedule/lesson/all",
        "/education/schedule/all",
        "/education/schedule/current",
        "/education/schedule/clearStatus",
        "/education/schedule/create",
        "/education/schedule/update",
        "/education/schedule/withLessons",
        "/education/schedule/withPasses",
        "/education/schedule/today",
        "/education/schedule/lesson/passes",
        "/education/schedule/lesson/pass",
        "/education/schedule/lesson/pass/update",
        "/education/schedule/lesson/passes/update",
        "/education/schedule/lesson/passes/week",
        "/education/schedule/lesson/passes/month",
        "/education/schedule/lesson/passes/semester",
        "/education/schedule/lesson/passes/group/month",
        "/education/section/all",
        "/education/section/create",
        "/education/section/update",
        "/education/section/",
        "/education/practice/update",
        "/education/practice/register",
        "/education/practice/",
        "/services",
        "/pupil"
    )

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val builder = originalRequest.newBuilder()

        val path: String = originalRequest.url.encodedPath
        if (pathsPartsWithAuth.any { path.startsWith(it) }) {
            val token = sharedPreferences.getString(
                if (CurrentUserTypeHolder.type == UserType.Admin) ADMIN_ACCESS_TOKEN_KEY else PUPIL_ACCESS_TOKEN_KEY,
                null
            )
            token?.let {
                builder.addHeader("Authorization", "Bearer $it")
            }
        }

        val requestWithToken = builder
            .url(originalRequest.url)
            .build()

        return chain.proceed(requestWithToken)
    }
}





























