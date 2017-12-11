package com.changjiashuai.data.source.cache

import com.changjiashuai.data.model.BufferooEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * Email: changjiashuai@gmail.com
 *
 * Created by CJS on 2017/12/11 14:50.
 */
interface BufferooCache {

    fun clearBufferoos(): Completable
    fun saveBufferoos(bufferoos: List<BufferooEntity>): Completable
    fun getBufferoos(): Flowable<List<BufferooEntity>>
    fun isCached(): Single<Boolean>
    fun setLastCacheTime(lastCache: Long)
    fun isExpired(): Boolean
}