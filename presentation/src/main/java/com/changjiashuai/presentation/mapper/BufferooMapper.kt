package com.changjiashuai.presentation.mapper

import com.changjiashuai.domain.executor.model.Bufferoo
import com.changjiashuai.presentation.model.BufferooView
import javax.inject.Inject

/**
 * Email: changjiashuai@gmail.com
 *
 * Created by CJS on 2017/12/12 10:08.
 */
open class BufferooMapper @Inject constructor() : Mapper<BufferooView, Bufferoo> {

    override fun mapToView(type: Bufferoo): BufferooView {
        return BufferooView(type.name, type.title, type.avatar)
    }
}