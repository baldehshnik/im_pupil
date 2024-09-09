package com.sparkfusion.core.hilt_core

import java.util.concurrent.TimeUnit

enum class NetworkTimeout(
    private val _value: Long,
    private val _type: TimeUnit
) {
    CONNECTION(16L, TimeUnit.SECONDS);

    val value: Long get() = _value
    val type: TimeUnit get() = _type
}