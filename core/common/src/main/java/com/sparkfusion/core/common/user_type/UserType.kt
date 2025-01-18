package com.sparkfusion.core.common.user_type

object CurrentUserTypeHolder {

    private var _type: UserType? = null
    var type
        get() = requireNotNull(_type)
        set(value) {
            _type = value
        }
}

sealed class UserType {
    data object Admin : UserType()
    data object Pupil : UserType()
}



