package com.changjiashuai.data.source.cache

import com.changjiashuai.data.fake.BufferooFactory
import com.changjiashuai.data.model.BufferooEntity
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Email: changjiashuai@gmail.com
 *
 *
 * Created by CJS on 2017/12/11 16:36.
 */
@RunWith(JUnit4::class)
class CacheBufferooDataSourceTest {

    private lateinit var bufferooCache: BufferooCache
    private lateinit var bufferooCacheDataSource: CacheBufferooDataSource

    @Before
    fun setUp() {
        bufferooCache = mock()
        bufferooCacheDataSource = CacheBufferooDataSource(bufferooCache)
    }

    @Test
    fun clearBufferoosCompletes() {
        stubBufferooCacheClearBufferoos(Completable.complete())
        val testObserver = bufferooCacheDataSource.clearBufferoos().test()
        testObserver.assertComplete()
    }

    @Test
    fun saveBufferoosCompletes() {
        stubBufferooCacheSaveBufferoos(Completable.complete())
        val testObserver = bufferooCacheDataSource.saveBufferoos(
                BufferooFactory.makeBufferooEntityList(2)
        ).test()
        testObserver.assertComplete()
    }

    @Test
    fun getBufferoosCompletes() {
        stubBufferooCacheGetBufferoos(Flowable.just(BufferooFactory.makeBufferooEntityList(2)))
        val testObserver = bufferooCacheDataSource.getBufferoos().test()
        testObserver.assertComplete()
    }

    private fun stubBufferooCacheSaveBufferoos(completable: Completable) {
        whenever(bufferooCache.saveBufferoos(any()))
                .thenReturn(completable)
    }

    private fun stubBufferooCacheGetBufferoos(single: Flowable<List<BufferooEntity>>) {
        whenever(bufferooCache.getBufferoos())
                .thenReturn(single)
    }

    private fun stubBufferooCacheClearBufferoos(completable: Completable) {
        whenever(bufferooCache.clearBufferoos())
                .thenReturn(completable)
    }
}