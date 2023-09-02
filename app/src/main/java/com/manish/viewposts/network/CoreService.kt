package com.manish.viewposts.network

import com.manish.viewposts.ktx.execute
import javax.inject.Inject

class CoreService @Inject constructor(private val coreAPI: CoreAPI) {
    suspend fun posts(onResult: Result<List<Post>>) {
        onResult.execute {
            coreAPI.posts()
        }
    }
}