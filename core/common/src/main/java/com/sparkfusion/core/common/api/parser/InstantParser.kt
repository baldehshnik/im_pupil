package com.sparkfusion.core.common.api.parser

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset

fun Instant.toLocalDateTime(): LocalDateTime {
    return LocalDateTime.ofEpochSecond(this.epochSecond, this.nano, ZoneOffset.UTC)
}



























