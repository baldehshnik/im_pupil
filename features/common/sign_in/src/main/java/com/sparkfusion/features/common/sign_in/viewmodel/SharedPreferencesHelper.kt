package com.sparkfusion.features.common.sign_in.viewmodel

import android.content.Context
import com.sparkfusion.core.common.shared_preferences.ADMIN_ACCESS_TOKEN_KEY
import com.sparkfusion.core.common.shared_preferences.ADMIN_REFRESH_TOKEN_KEY
import com.sparkfusion.core.common.shared_preferences.ADMIN_TYPE_KEY
import com.sparkfusion.core.common.shared_preferences.LOGIN_SHARED_PREFERENCES_NAME
import com.sparkfusion.core.common.shared_preferences.PUPIL_ACCESS_TOKEN_KEY
import com.sparkfusion.core.common.shared_preferences.PUPIL_REFRESH_TOKEN_KEY
import com.sparkfusion.core.common.shared_preferences.PUPIL_TYPE_KEY
import com.sparkfusion.core.common.user_type.CurrentUserTypeHolder
import com.sparkfusion.core.common.user_type.UserType

class SharedPreferencesHelper(context: Context) {

    private val sharedPreferences = context.getSharedPreferences(
        LOGIN_SHARED_PREFERENCES_NAME,
        Context.MODE_PRIVATE
    )

    fun saveLoginData(accessToken: String, refreshToken: String) {
        when (CurrentUserTypeHolder.type) {
            UserType.Admin -> {
                sharedPreferences.edit()
                    .putString(ADMIN_TYPE_KEY, CurrentUserTypeHolder.type.toString())
                    .putString(ADMIN_ACCESS_TOKEN_KEY, accessToken)
                    .putString(ADMIN_REFRESH_TOKEN_KEY, refreshToken)
                    .apply()
            }

            UserType.Pupil -> {
                sharedPreferences.edit()
                    .putString(PUPIL_TYPE_KEY, CurrentUserTypeHolder.type.toString())
                    .putString(PUPIL_ACCESS_TOKEN_KEY, accessToken)
                    .putString(PUPIL_REFRESH_TOKEN_KEY, refreshToken)
                    .apply()
            }
        }
    }
}










