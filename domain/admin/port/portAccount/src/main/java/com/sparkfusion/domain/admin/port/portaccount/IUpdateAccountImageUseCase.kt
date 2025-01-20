package com.sparkfusion.domain.admin.port.portaccount

import com.sparkfusion.core.common.result.Answer
import java.io.File

interface IUpdateAccountImageUseCase {

    suspend fun updateImage(file: File): Answer<AdminNewImageModel>
}