package com.sparkfusion.core.common.api

import com.sparkfusion.core.common.exception.NetworkException
import com.sparkfusion.core.common.exception.UnexpectedException
import com.sparkfusion.core.common.result.Answer
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.io.IOException

suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher,
    call: suspend () -> Answer<T>
): Answer<T> = withContext(dispatcher) {
    try {
        call.invoke()
    } catch (exception: Exception) {
        handleApiException(exception)
    }
}

private fun handleApiException(exception: Exception): Answer.Failure {
    return Answer.Failure(
        when (exception) {
            is IOException -> NetworkException(exception)
            else -> UnexpectedException(exception)
        }
    )
}