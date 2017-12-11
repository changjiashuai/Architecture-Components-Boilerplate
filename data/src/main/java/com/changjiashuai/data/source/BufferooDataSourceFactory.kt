package com.changjiashuai.data.source

import com.changjiashuai.data.source.cache.BufferooCache
import com.changjiashuai.data.source.cache.CacheBufferooDataSource
import com.changjiashuai.data.source.remote.RemoteBufferooDataSource
import javax.inject.Inject

/**
 * Email: changjiashuai@gmail.com
 *
 * Created by CJS on 2017/12/11 14:56.
 *
 * fixme: may be field inject???
 */
open class BufferooDataSourceFactory @Inject constructor(
        private val bufferooCache: BufferooCache,
        private val cacheBufferooDataSource: CacheBufferooDataSource,
        private val remoteBufferooDataSource: RemoteBufferooDataSource
) {

    open fun retrieveDataSource(isCached: Boolean): BufferooDataSource {
        if (isCached && !bufferooCache.isExpired()) {
            return retrieveCacheDataSource()
        }
        return retrieveRemoteDataSource()
    }

    open fun retrieveCacheDataSource(): BufferooDataSource {
        return cacheBufferooDataSource
    }

    open fun retrieveRemoteDataSource(): BufferooDataSource {
        return remoteBufferooDataSource
    }
}