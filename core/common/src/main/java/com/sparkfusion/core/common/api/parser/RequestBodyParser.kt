package com.sparkfusion.core.common.api.parser

import com.google.gson.Gson
import okhttp3.MediaType
import okhttp3.RequestBody
import javax.inject.Inject

class RequestBodyParser @Inject constructor() {

    fun <T> parse(value: T): RequestBody {
        val json = Gson().toJson(value)
        return RequestBody.create(MediaType.parse("application/json"), json)
    }
}