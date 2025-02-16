package com.sparkfusion.core.common.result

import android.util.Log
import com.sparkfusion.core.common.exception.AnswerHasNoMapperException
import com.sparkfusion.core.common.exception.AnswerMappingException
import com.sparkfusion.core.common.exception.ImPupilException

sealed class Answer<out T> {

    abstract fun unwrap(): T
    abstract suspend fun <R> suspendMap(mapper: (suspend (T) -> R)? = null): Answer<R>

    data class Success<out T>(private val value: T) : Answer<T>() {

        override fun unwrap(): T = value

        override suspend fun <R> suspendMap(mapper: (suspend (T) -> R)?): Answer<R> {
            return if (mapper == null) Failure(AnswerHasNoMapperException())
            else try {
                Success(mapper(unwrap()))
            } catch (e: Exception) {
                Log.d("TAGTAG", e.message.toString())
                Failure(AnswerMappingException())
            }
        }
    }

    data class Failure(val exception: ImPupilException) : Answer<Nothing>() {

        override fun unwrap(): Nothing = throw exception

        override suspend fun <R> suspendMap(mapper: (suspend (Nothing) -> R)?): Answer<R> {
            return this
        }
    }

    inline fun onSuccess(action: (T) -> Unit): Answer<T> {
        if (this is Success) action(unwrap())
        return this
    }

    inline fun onFailure(action: (ImPupilException) -> Unit): Answer<T> {
        if (this is Failure) action(exception)
        return this
    }
}