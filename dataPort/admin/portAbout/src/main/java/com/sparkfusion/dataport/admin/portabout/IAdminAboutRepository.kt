package com.sparkfusion.dataport.admin.portabout

import com.sparkfusion.core.common.result.Answer
import java.io.File

interface IAdminAboutRepository {

    suspend fun updateAboutBlock(
        aboutId: Int?,
        description: String?,
        icon: String?,
        image: File?
    ): Answer<Unit>
}



























