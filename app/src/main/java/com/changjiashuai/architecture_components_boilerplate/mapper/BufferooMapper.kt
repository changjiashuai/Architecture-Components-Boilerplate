package com.changjiashuai.architecture_components_boilerplate.mapper

import com.changjiashuai.architecture_components_boilerplate.model.BufferooViewModel
import com.changjiashuai.presentation.model.BufferooView
import javax.inject.Inject

/**
 * Email: changjiashuai@gmail.com
 *
 * Created by CJS on 2017/12/12 14:37.
 */
open class BufferooMapper @Inject constructor() : Mapper<BufferooViewModel, BufferooView> {

    override fun mapToViewModel(type: BufferooView): BufferooViewModel {
        return BufferooViewModel(type.name, type.title, type.avatar)
    }
}