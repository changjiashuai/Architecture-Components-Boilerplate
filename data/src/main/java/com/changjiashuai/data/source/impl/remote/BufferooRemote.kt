package com.changjiashuai.data.source.impl.remote

import com.changjiashuai.data.model.BufferooEntity
import io.reactivex.Flowable

/**
 * Email: changjiashuai@gmail.com
 *
 * Created by CJS on 2017/12/11 14:47.
 */
interface BufferooRemote {

    fun getBufferoos(): Flowable<List<BufferooEntity>>
}