package com.sparkfusion.core.common.exception

/***
 * The default class of all exceptions in the application.
 */
open class ImPupilException(
    message: String? = "Application exception occurred",
    cause: Throwable? = null
): Exception(message, cause)

/***
 * The default exception that occurs wherever an exception is not known.
 */
class UnexpectedException(cause: Throwable? = null) : ImPupilException(cause?.message ?: "Occurred unknown exception", cause)

/***
 * The exception that occurs when connection is failed.
 */
class NetworkException(cause: Throwable) : ImPupilException(cause.message, cause)

/***
 * The exception that occurs when a data is not found on the backend.
 */
class NotFoundException : ImPupilException("The data was not found")

/***
 * The exception that occurs when a successful answer data are mapping without mapper.
 */
internal class AnswerHasNoMapperException : ImPupilException("Can't map a success answer without mapper")

/***
 * The exception that occurs when a successful answer data have a mapping error.
 */
internal class AnswerMappingException : ImPupilException("An Exception was occurred while the answer was being mapped")