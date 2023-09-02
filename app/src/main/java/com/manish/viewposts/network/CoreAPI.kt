package com.manish.viewposts.network

import retrofit2.Response
import retrofit2.http.GET

interface CoreAPI {
    @GET("posts")
    suspend fun posts(): Response<List<Post>>
}