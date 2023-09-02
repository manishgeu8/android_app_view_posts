package com.manish.viewposts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.manish.viewposts.core.BaseViewModel
import com.manish.viewposts.network.Post
import com.manish.viewposts.network.CoreService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val coreService: CoreService) : BaseViewModel() {
    private val _data = MutableLiveData<List<Post>>()
    val data: LiveData<List<Post>> get() = _data

    init {
        fetchData()
    }

    fun fetchData() {
        loading()
        viewModelScope.launch {
            coreService.posts(
                onResult = responseCallBack(
                    onSuccess = { response ->
                        _data.value = response
                        loaded()
                    },
                    onError = {
                        error()
                    }
                )
            )
        }
    }
}
