package com.sparkfusion.core.common.api

import android.util.Log
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
            Log.i("TAGTAG", "" + response.code() + " - " + response.errorBody()?.string().toString())
            Answer.Failure(handleExceptionCode(response.code()))
        }
    }
}

class ApiResponseHandler<R>(
    private val response: Response<R>,
    private val handleExceptionCode: (code: Int) -> ImPupilException = ::handleExceptionCode
) {

    fun handleFetchedData(): Answer<R> {
        return if (response.isSuccessful) {
            val body = response.body()

            if (body == null) {
                Log.i("TAGTAG", "My null")
                Answer.Failure(NotFoundException())
            } else {
                Log.i("TAGTAG", "All is ok")
                Answer.Success(body)
            }
        } else {
            Log.i("TAGTAG", "" + response.code() + " - " + response.errorBody()?.string().toString())
            Answer.Failure(handleExceptionCode(response.code()))
        }
    }
}

private fun handleExceptionCode(code: Int): ImPupilException {
    return when (code) {
        404 -> NotFoundException()
        else -> UnexpectedException()
    }
}









