package com.changjiashuai.data.source

import com.changjiashuai.data.model.BufferooEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * Email: changjiashuai@gmail.com
 *
 * Created by CJS on 2017/12/11 14:38.
 */
interface BufferooDataSource {

    fun clearBufferoos(): Completable
    fun saveBufferoos(bufferoos: List<BufferooEntity>): Completable
    fun getBufferoos(): Flowable<List<BufferooEntity>>
    fun isCached(): Single<Boolean>
}