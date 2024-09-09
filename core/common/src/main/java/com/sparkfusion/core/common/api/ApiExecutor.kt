package com.sparkfusion.core.common.api

import retrofit2.Response

class ApiListResponseHandler<R>(private val response: Response<List<R>>) {

    fun handleFetchedData(): List<R>? {
        if (response.isSuccessful) {
            return response.body()
        } else {
            // error handler
        }
        TODO()
    }
}