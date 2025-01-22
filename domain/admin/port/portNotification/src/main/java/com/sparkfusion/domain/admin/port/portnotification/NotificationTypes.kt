package com.sparkfusion.domain.admin.port.portnotification

enum class NotificationTypes(private val _value: Int) {
    UNKNOWN(0),
    NEW_ADMIN(1),
    NEW_PUPIL(2);

    val value: Int
        get() = _value

    companion object {

        @JvmStatic
        fun fromValue(value: Int): NotificationTypes {
            return entries.find { it.value == value } ?: UNKNOWN
        }
    }
}


























