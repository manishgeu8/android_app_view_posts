package com.manish.viewposts.ktx

import com.manish.viewposts.network.Result
import retrofit2.Response

suspend fun <T : Any> Result<T>.execute(call: suspend () -> Response<T>) {
    runCatching {
        val response = call.invoke()

        when (response.isSuccessful) {
            true -> onSuccess(response.body())
            else -> onError(response)
        }
    }.onFailure {
        onFailure(it)
    }
}