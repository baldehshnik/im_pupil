package com.sparkfusion.features.common.sign_in.viewmodel

import android.content.Context
import com.sparkfusion.core.common.shared_preferences.ACCESS_TOKEN_KEY
import com.sparkfusion.core.common.shared_preferences.LOGIN_SHARED_PREFERENCES_NAME
import com.sparkfusion.core.common.shared_preferences.REFRESH_TOKEN_KEY
import com.sparkfusion.core.common.shared_preferences.USER_TYPE_KEY
import com.sparkfusion.core.common.user_type.CurrentUserTypeHolder
import com.sparkfusion.domain.admin.port.portauth.JwtAuthenticationModel

class SharedPreferencesHelper(context: Context) {

    private val sharedPreferences = context.getSharedPreferences(
        LOGIN_SHARED_PREFERENCES_NAME,
        Context.MODE_PRIVATE
    )

    fun saveLoginDataForAdmin(model: JwtAuthenticationModel) {
        sharedPreferences.edit()
            .putString(USER_TYPE_KEY, CurrentUserTypeHolder.type.toString())
            .putString(ACCESS_TOKEN_KEY, model.accessToken)
            .putString(REFRESH_TOKEN_KEY, model.refreshToken)
            .apply()
    }
}










