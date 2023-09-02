package com.manish.viewposts.network

import retrofit2.Response

interface Result<T : Any> {
    fun onSuccess(value: T?)
    fun onError(response: Response<T>)
    fun onFailure(value: Throwable)
}