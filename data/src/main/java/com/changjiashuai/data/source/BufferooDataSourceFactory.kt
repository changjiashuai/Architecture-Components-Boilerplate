package com.changjiashuai.data.source

import com.changjiashuai.data.source.impl.cache.BufferooCache
import com.changjiashuai.data.source.impl.cache.CacheBufferooDataSourceImpl
import com.changjiashuai.data.source.impl.remote.RemoteBufferooDataSourceImpl
import javax.inject.Inject

/**
 * Email: changjiashuai@gmail.com
 *
 * Created by CJS on 2017/12/11 14:56.
 *
 * fixme: may be field inject???
 */
class BufferooDataSourceFactory @Inject constructor(
        private val bufferooCache: BufferooCache,
        private val cacheBufferooDataSourceImpl: CacheBufferooDataSourceImpl,
        private val remoteBufferooDataSourceImpl: RemoteBufferooDataSourceImpl
) {

    open fun retrieveDataSource(isCached: Boolean): BufferooDataSource {
        if (isCached && !bufferooCache.isExpired()) {
            return retrieveCacheDataSource()
        }
        return retrieveRemoteDataSource()
    }

    open fun retrieveCacheDataSource(): BufferooDataSource {
        return cacheBufferooDataSourceImpl
    }

    open fun retrieveRemoteDataSource(): BufferooDataSource {
        return remoteBufferooDataSourceImpl
    }
}