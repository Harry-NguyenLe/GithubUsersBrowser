package com.tymex.interview.user_data.extension

import com.tymex.interview.core.utils.Resource
import com.tymex.interview.core.model.ResponseModel
import retrofit2.Response
import java.io.IOException
import java.net.UnknownHostException

suspend fun <T : ResponseModel> handleAPICall(
    apiCall: suspend () -> Response<T>
): Resource<T> {
    return try {
        apiCall.invoke().handleAPIResponse()
    } catch (e: Exception) {
        e.printStackTrace()
        return when (e) {
            is UnknownHostException -> Resource.Failure(IOException())
            else -> Resource.Failure(IOException("Unknown error"))
        }
    }
}

private fun <T : ResponseModel> Response<T>.handleAPIResponse(): Resource<T> {
    val responseBody = body() as T
    if (isSuccess()) {
        return Resource.Success(responseBody)
    }
    return Resource.Failure(
        IOException()
    )
}

private fun <T : ResponseModel> Response<T>.isSuccess(): Boolean = isSuccessful