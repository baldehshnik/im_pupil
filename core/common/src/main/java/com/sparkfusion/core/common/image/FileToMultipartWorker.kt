package com.sparkfusion.core.common.image

import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import javax.inject.Inject

class FileToMultipartWorker @Inject constructor(
) {

    fun convert(image: File, name: String = "image"): MultipartBody.Part {
        val body = RequestBody.create(MediaType.parse("image/jpeg"), image)
        return MultipartBody.Part.createFormData("image", image.name, body)
    }
}






