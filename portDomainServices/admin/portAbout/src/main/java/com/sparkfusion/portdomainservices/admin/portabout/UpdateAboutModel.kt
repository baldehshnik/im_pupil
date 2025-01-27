package com.sparkfusion.portdomainservices.admin.portabout

import java.io.File

data class UpdateAboutModel(
    val id: Int?,
    val description: String?,
    val icon: String?,
    val file: File? = null
)
