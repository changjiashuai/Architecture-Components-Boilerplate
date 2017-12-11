package com.changjiashuai.data.source.cache

import com.changjiashuai.data.model.BufferooEntity
import com.changjiashuai.data.source.BufferooDataSource
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Email: changjiashuai@gmail.com
 *
 * Created by CJS on 2017/12/11 14:50.
 */
open class CacheBufferooDataSource @Inject constructor(
        private val bufferooCache: BufferooCache
) : BufferooDataSource {

    override fun clearBufferoos(): Completable {
        return bufferooCache.clearBufferoos()
    }

    override fun saveBufferoos(bufferoos: List<BufferooEntity>): Completable {
        return bufferooCache.saveBufferoos(bufferoos)
    }

    override fun getBufferoos(): Flowable<List<BufferooEntity>> {
        return bufferooCache.getBufferoos()
    }

    override fun isCached(): Single<Boolean> {
        return bufferooCache.isCached()
    }
}