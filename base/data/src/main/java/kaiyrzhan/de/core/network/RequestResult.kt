package kaiyrzhan.de.core.network

import retrofit2.HttpException

sealed interface RequestResult<out T>

class RequestSuccess<T>(val data: T) : RequestResult<T>
class RequestError(val code: Int, val message: String?) : RequestResult<Nothing>
class RequestException(val e: Throwable) : RequestResult<Nothing>


suspend fun <T : Any> RequestResult<T>.onSuccess(
    executable: suspend (T) -> Unit
): RequestResult<T> = apply {
    if (this is RequestSuccess) {
        executable(data)
    }
}


suspend fun <T : Any> RequestResult<T>.onError(
    executable: suspend (code: Int, message: String?) -> Unit
): RequestResult<T> = apply {
    if (this is RequestError) {
        executable(code, message)
    }
}

suspend fun <T : Any> RequestResult<T>.onException(
    executable: suspend (e: Throwable) -> Unit
): RequestResult<T> = apply {
    if (this is RequestException) {
        executable(e)
    }
}

inline fun <T, R> RequestResult<T>.map(transform: (T) -> R): RequestResult<R> {
    return when (this) {
        is RequestError -> this
        is RequestException -> this
        is RequestSuccess -> RequestSuccess(transform(data))
    }
}

fun <T : Any> Result<T>.toRequestResult(): RequestResult<T> {
    return when {
        isSuccess -> RequestSuccess(getOrThrow())
        isFailure -> {
            when (val exception = exceptionOrNull()) {
                is HttpException -> RequestError(
                    code = exception.code(),
                    message = exception.message()
                )
                is Throwable -> RequestException(exception)
                else -> error("Impossible branch")
            }
        }
        else -> error("Impossible branch")
    }
}





