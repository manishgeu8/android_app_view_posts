package com.manish.viewposts.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.manish.viewposts.network.Result
import retrofit2.Response

abstract class BaseViewModel : ViewModel() {

    private val viewStateInternalLiveData = MutableLiveData<ViewModelState>()
    val viewStateLiveData: LiveData<ViewModelState>
        get() = viewStateInternalLiveData

    protected open fun loading() {
        viewStateInternalLiveData.postValue(ViewModelState(state = ViewState.Loading))
    }

    protected open fun loaded() {
        viewStateInternalLiveData.postValue(ViewModelState(state = ViewState.Loaded))
    }

    protected open fun error() {
        viewStateInternalLiveData.postValue(ViewModelState(state = ViewState.Error))
    }

    fun <T : Any> responseCallBack(
        onError: ((String?) -> Unit)? = null,
        onSuccess: (value: T) -> Unit
    ) = object : Result<T> {
        override fun onSuccess(value: T?) {
            value?.apply {
                onSuccess(this)
            } ?: onError?.invoke(null)
        }

        override fun onError(response: Response<T>) {
            onError?.invoke(response.errorBody()?.toString())
        }

        override fun onFailure(value: Throwable) {
            onError?.invoke(value.localizedMessage)
        }
    }
}

data class ViewModelState(
    val state: ViewState,
    val title: String? = null,
    val message: String? = null
)

enum class ViewState {
    Loading,
    Loaded,
    Error
}