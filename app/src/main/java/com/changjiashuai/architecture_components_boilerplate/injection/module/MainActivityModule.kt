package com.changjiashuai.architecture_components_boilerplate.injection.module

import com.changjiashuai.domain.interactor.browse.GetBufferoos
import com.changjiashuai.presentation.browse.BrowseBufferoosViewModelFactory
import com.changjiashuai.presentation.mapper.BufferooMapper
import dagger.Module
import dagger.Provides

/**
 * Email: changjiashuai@gmail.com
 *
 * Created by CJS on 2017/12/12 15:04.
 */
@Module
open class MainActivityModule {

    @Provides
    fun provideBrowseBufferoosViewModelFactory(getBufferoos: GetBufferoos,
                                               bufferooMapper: BufferooMapper): BrowseBufferoosViewModelFactory {
        return BrowseBufferoosViewModelFactory(getBufferoos, bufferooMapper)
    }
}