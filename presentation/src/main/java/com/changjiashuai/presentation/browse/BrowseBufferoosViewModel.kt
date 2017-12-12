package com.changjiashuai.presentation.browse

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.changjiashuai.domain.executor.model.Bufferoo
import com.changjiashuai.domain.interactor.browse.GetBufferoos
import com.changjiashuai.presentation.data.Resource
import com.changjiashuai.presentation.data.ResourceState
import com.changjiashuai.presentation.mapper.BufferooMapper
import com.changjiashuai.presentation.model.BufferooView
import io.reactivex.subscribers.DisposableSubscriber
import javax.inject.Inject

/**
 * Email: changjiashuai@gmail.com
 *
 * Created by CJS on 2017/12/12 10:13.
 */
open class BrowseBufferoosViewModel @Inject internal constructor(
        private val getBufferoos: GetBufferoos,
        private val bufferooMapper: BufferooMapper
) : ViewModel() {

    private val bufferoosLiveData: MutableLiveData<Resource<List<BufferooView>>> = MutableLiveData()

    init {
        fetchBufferoos()
    }

    override fun onCleared() {
        getBufferoos.dispose()
        super.onCleared()
    }

    fun getBufferoos(): LiveData<Resource<List<BufferooView>>> {
        return bufferoosLiveData
    }

    fun fetchBufferoos() {
        bufferoosLiveData.postValue(Resource(ResourceState.LOADING, null, null))
        getBufferoos.execute(BufferooSubscriber())
    }

    inner class BufferooSubscriber : DisposableSubscriber<List<Bufferoo>>() {

        override fun onComplete() {}

        override fun onNext(t: List<Bufferoo>) {
            bufferoosLiveData.postValue(Resource(ResourceState.SUCCESS,
                    t.map { bufferooMapper.mapToView(it) }, null))
        }

        override fun onError(t: Throwable) {
            bufferoosLiveData.postValue(Resource(ResourceState.ERROR, null, t.message))
        }
    }
}