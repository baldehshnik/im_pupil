package com.sparkfusion.features.common.welcome.viewmodel

import android.content.Context
import com.sparkfusion.core.common.shared_preferences.ADMIN_ACCESS_TOKEN_KEY
import com.sparkfusion.core.common.shared_preferences.ADMIN_TYPE_KEY
import com.sparkfusion.core.common.shared_preferences.LOGIN_SHARED_PREFERENCES_NAME
import com.sparkfusion.core.common.shared_preferences.PUPIL_ACCESS_TOKEN_KEY
import com.sparkfusion.core.common.shared_preferences.PUPIL_TYPE_KEY
import com.sparkfusion.core.common.user_type.UserType

class LoginDataProvider(context: Context) {

    private val sharedPreferences = context.getSharedPreferences(
        LOGIN_SHARED_PREFERENCES_NAME,
        Context.MODE_PRIVATE
    )

    fun provideAccessTokenByUserType(type: UserType): String? {
        val savedType = sharedPreferences.getString(
            if (type == UserType.Admin) ADMIN_TYPE_KEY else PUPIL_TYPE_KEY,
            ""
        )

        if (type.toString() != savedType) return null
        return sharedPreferences.getString(
            if (type == UserType.Admin) ADMIN_ACCESS_TOKEN_KEY else PUPIL_ACCESS_TOKEN_KEY,
            ""
        )
    }
}














