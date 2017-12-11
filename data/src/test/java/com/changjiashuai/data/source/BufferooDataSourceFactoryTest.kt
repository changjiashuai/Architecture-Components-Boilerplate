package com.changjiashuai.data.source

import com.changjiashuai.data.source.impl.cache.BufferooCache
import com.changjiashuai.data.source.impl.cache.CacheBufferooDataSourceImpl
import com.changjiashuai.data.source.impl.remote.RemoteBufferooDataSourceImpl
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Email: changjiashuai@gmail.com
 *
 * Created by CJS on 2017/12/11 15:36.
 */
@RunWith(JUnit4::class)
class BufferooDataSourceFactoryTest {

    private lateinit var bufferooCache: BufferooCache
    private lateinit var cacheBufferooDataSourceImpl: CacheBufferooDataSourceImpl
    private lateinit var remoteBufferooDataSourceImpl: RemoteBufferooDataSourceImpl

    private lateinit var bufferooDataSourceFactory: BufferooDataSourceFactory

    @Before
    fun setUp() {
        bufferooCache = mock()
        cacheBufferooDataSourceImpl = mock()
        remoteBufferooDataSourceImpl = mock()

        bufferooDataSourceFactory = BufferooDataSourceFactory(bufferooCache,
                cacheBufferooDataSourceImpl, remoteBufferooDataSourceImpl)
    }

    @Test
    fun retrieveDataSourceWhenNotCachedReturnRemoteDataSource() {
        stubBufferooCacheIsCached(Single.just(false))
        val bufferooDataSource = bufferooDataSourceFactory.retrieveDataSource(false)
        assert(bufferooDataSource is RemoteBufferooDataSourceImpl)
    }

    @Test
    fun retrieveDataSourceWhenCacheExpiredReturnRemoteDataSource() {
        stubBufferooCacheIsCached(Single.just(true))
        stubBufferooCacheIsExpired(true)
        val bufferooDataSource = bufferooDataSourceFactory.retrieveDataSource(true)
        assert(bufferooDataSource is RemoteBufferooDataSourceImpl)
    }

    @Test
    fun retrieveDataSourceReturnCacheDataSource() {
        stubBufferooCacheIsCached(Single.just(true))
        stubBufferooCacheIsExpired(false)
        val bufferooDataSource = bufferooDataSourceFactory.retrieveDataSource(true)
        assert(bufferooDataSource is CacheBufferooDataSourceImpl)
    }

    @Test
    fun retrieveCacheDataSource() {
        val bufferooDataSource = bufferooDataSourceFactory.retrieveCacheDataSource()
        assert(bufferooDataSource is CacheBufferooDataSourceImpl)
    }

    @Test
    fun retrieveRemoteDataSource() {
        val bufferooDataSource = bufferooDataSourceFactory.retrieveRemoteDataSource()
        assert(bufferooDataSource is RemoteBufferooDataSourceImpl)
    }

    private fun stubBufferooCacheIsCached(single: Single<Boolean>) {
        whenever(bufferooCache.isCached()).thenReturn(single)
    }

    private fun stubBufferooCacheIsExpired(isExpired: Boolean) {
        whenever(bufferooCache.isExpired()).thenReturn(isExpired)
    }
}