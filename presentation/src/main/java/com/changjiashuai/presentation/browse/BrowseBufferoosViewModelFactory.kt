package com.changjiashuai.presentation.browse

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.changjiashuai.domain.interactor.browse.GetBufferoos
import com.changjiashuai.presentation.mapper.BufferooMapper

/**
 * Email: changjiashuai@gmail.com
 *
 * Created by CJS on 2017/12/12 10:20.
 */
open class BrowseBufferoosViewModelFactory(
        private val getBufferoos: GetBufferoos,
        private val bufferooMapper: BufferooMapper
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BrowseBufferoosViewModel::class.java)) {
            return BrowseBufferoosViewModel(getBufferoos, bufferooMapper) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}