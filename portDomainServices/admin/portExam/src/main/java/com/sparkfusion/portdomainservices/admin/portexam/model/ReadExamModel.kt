package com.sparkfusion.portdomainservices.admin.portexam.model

import java.time.Instant

data class ReadExamModel(
    val id: Int,
    val type: Int,
    val name: String,
    val audience: String,
    val dateTime: Instant,
    val status: Int
)
