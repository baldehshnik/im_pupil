package com.sparkfusion.core.hilt_core

import java.util.concurrent.TimeUnit

internal enum class NetworkTimeout(
    private val _value: Long,
    private val _type: TimeUnit
) {
    CONNECTION(16L, TimeUnit.SECONDS),
    WRITE(32L, TimeUnit.SECONDS);

    internal val value: Long get() = _value
    internal val type: TimeUnit get() = _type
}