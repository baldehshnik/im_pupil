package com.sparkfusion.core.common.api

import com.sparkfusion.core.common.exception.ImPupilException
import com.sparkfusion.core.common.exception.NotFoundException
import com.sparkfusion.core.common.exception.UnexpectedException
import com.sparkfusion.core.common.result.Answer
import retrofit2.Response

class ApiListResponseHandler<R>(private val response: Response<List<R>>) {

    fun handleFetchedData(): Answer<List<R>> {
        return if (response.isSuccessful) {
            Answer.Success(response.body() ?: emptyList())
        } else {
            Answer.Failure(handleExceptionCode(response.code()))
        }
    }

    private fun handleExceptionCode(code: Int): ImPupilException {
        return when (code) {
            404 -> NotFoundException()
            else -> UnexpectedException()
        }
    }
}