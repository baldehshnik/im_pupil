package com.sparkfusion.portdomainservices.admin.portmagazine.model

import java.time.Instant

data class ReadPassModel(
    val id: Int?,
    val date: Instant,
    val status: Int
)
