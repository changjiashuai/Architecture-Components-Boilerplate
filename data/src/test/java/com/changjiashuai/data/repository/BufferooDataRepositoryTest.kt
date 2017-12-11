package com.changjiashuai.data.repository

import com.changjiashuai.data.fake.BufferooFactory
import com.changjiashuai.data.mapper.BufferooMapper
import com.changjiashuai.data.model.BufferooEntity
import com.changjiashuai.data.source.BufferooDataSource
import com.changjiashuai.data.source.BufferooDataSourceFactory
import com.changjiashuai.data.source.impl.cache.CacheBufferooDataSourceImpl
import com.changjiashuai.data.source.impl.remote.RemoteBufferooDataSourceImpl
import com.changjiashuai.domain.executor.model.Bufferoo
import com.nhaarman.mockito_kotlin.*
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Email: changjiashuai@gmail.com
 *
 * Created by CJS on 2017/12/11 15:52.
 */
@RunWith(JUnit4::class)
class BufferooDataRepositoryTest {

    private lateinit var bufferooDataSourceFactory: BufferooDataSourceFactory
    private lateinit var bufferooMapper: BufferooMapper
    private lateinit var cacheBufferooDataSource: CacheBufferooDataSourceImpl
    private lateinit var remoteBufferooDataSource: RemoteBufferooDataSourceImpl

    private lateinit var bufferooDataRepository: BufferooDataRepository

    @Before
    fun setUp() {
        bufferooDataSourceFactory = mock()
        bufferooMapper = mock()
        cacheBufferooDataSource = mock()
        remoteBufferooDataSource = mock()
        bufferooDataRepository = BufferooDataRepository(bufferooDataSourceFactory, bufferooMapper)
        stubBufferooDataSourceFactoryRetrieveCacheDataSource()
        stubBufferooDataSourceFactoryRetrieveRemoteDataSource()
    }

    @Test
    fun clearBufferoosCompletes() {
        stubBufferooCacheClearBufferoos(Completable.complete())
        val testObserver = bufferooDataRepository.clearBufferoos().test()
        testObserver.assertComplete()
    }

    @Test
    fun clearBufferoosCallByCacheDataSource() {
        stubBufferooCacheClearBufferoos(Completable.complete())
        bufferooDataRepository.clearBufferoos().test()
        verify(cacheBufferooDataSource).clearBufferoos()
    }

    @Test
    fun clearBufferoosNeverCallByRemoteDataSource() {
        stubBufferooCacheClearBufferoos(Completable.complete())
        bufferooDataRepository.clearBufferoos().test()
        verify(remoteBufferooDataSource, never()).clearBufferoos()
    }

    @Test
    fun saveBufferoosCompletes() {
        stubBufferooCacheSaveBufferoos(Completable.complete())
        val testObserver = bufferooDataRepository.saveBufferoos(
                BufferooFactory.makeBufferooList(2)
        ).test()
        testObserver.assertComplete()
    }

    @Test
    fun saveBufferoosCallByCacheDataSource() {
        stubBufferooCacheSaveBufferoos(Completable.complete())
        bufferooDataRepository.saveBufferoos(BufferooFactory.makeBufferooList(2))
        verify(cacheBufferooDataSource).saveBufferoos(any())
    }

    @Test
    fun saveBufferoosNeverCallByRemoteDataSource() {
        stubBufferooCacheSaveBufferoos(Completable.complete())
        bufferooDataRepository.saveBufferoos(BufferooFactory.makeBufferooList(2))
        verify(remoteBufferooDataSource, never()).saveBufferoos(any())
    }

    @Test
    fun getBufferoosCompletes() {
        stubBufferooCacheDataSourceIsCached(Single.just(true))
        stubBufferooDataSourceFactoryRetrieveDataSource(cacheBufferooDataSource)
        stubBufferooCacheDataSourceGetBufferoos(Flowable.just(
                BufferooFactory.makeBufferooEntityList(2)
        ))
        stubBufferooCacheSaveBufferoos(Completable.complete())
        val testObserver = bufferooDataRepository.getBufferoos().test()
        testObserver.assertComplete()
    }

    @Test
    fun getBufferoosReturnData() {
        stubBufferooCacheDataSourceIsCached(Single.just(true))
        stubBufferooDataSourceFactoryRetrieveDataSource(cacheBufferooDataSource)
        stubBufferooCacheSaveBufferoos(Completable.complete())
        val bufferoos = BufferooFactory.makeBufferooList(2)
        val bufferooEntities = BufferooFactory.makeBufferooEntityList(2)
        bufferoos.forEachIndexed { index, bufferoo ->
            stubBufferooMapperMapFromEntity(bufferooEntities[index], bufferoo)
        }
        stubBufferooCacheDataSourceGetBufferoos(Flowable.just(bufferooEntities))
        val testObserver = bufferooDataRepository.getBufferoos().test()
        testObserver.assertValue(bufferoos)
    }

    @Test
    fun getBufferoosSaveBufferoosWhenFromCacheDataSource() {
        stubBufferooDataSourceFactoryRetrieveDataSource(cacheBufferooDataSource)
        stubBufferooCacheSaveBufferoos(Completable.complete())
        bufferooDataRepository.saveBufferoos(BufferooFactory.makeBufferooList(2)).test()
        verify(cacheBufferooDataSource).saveBufferoos(any())
    }

    @Test
    fun getBufferoosNeverSaveBufferoosWhenFromRemoteDataSource() {
        stubBufferooDataSourceFactoryRetrieveDataSource(remoteBufferooDataSource)
        stubBufferooCacheSaveBufferoos(Completable.complete())
        bufferooDataRepository.saveBufferoos(BufferooFactory.makeBufferooList(2)).test()
        verify(remoteBufferooDataSource, never()).saveBufferoos(any())
    }

    private fun stubBufferooCacheSaveBufferoos(completable: Completable) {
        whenever(cacheBufferooDataSource.saveBufferoos(any()))
                .thenReturn(completable)
    }

    private fun stubBufferooCacheDataSourceIsCached(single: Single<Boolean>) {
        whenever(cacheBufferooDataSource.isCached())
                .thenReturn(single)
    }

    private fun stubBufferooCacheDataSourceGetBufferoos(single: Flowable<List<BufferooEntity>>) {
        whenever(cacheBufferooDataSource.getBufferoos())
                .thenReturn(single)
    }

    private fun stubBufferooCacheClearBufferoos(completable: Completable) {
        whenever(cacheBufferooDataSource.clearBufferoos())
                .thenReturn(completable)
    }

    private fun stubBufferooDataSourceFactoryRetrieveCacheDataSource() {
        whenever(bufferooDataSourceFactory.retrieveCacheDataSource())
                .thenReturn(cacheBufferooDataSource)
    }

    private fun stubBufferooDataSourceFactoryRetrieveRemoteDataSource() {
        whenever(bufferooDataSourceFactory.retrieveRemoteDataSource())
                .thenReturn(remoteBufferooDataSource)
    }

    private fun stubBufferooDataSourceFactoryRetrieveDataSource(dataSource: BufferooDataSource) {
        whenever(bufferooDataSourceFactory.retrieveDataSource(any()))
                .thenReturn(dataSource)
    }

    private fun stubBufferooMapperMapFromEntity(bufferooEntity: BufferooEntity, bufferoo: Bufferoo) {
        whenever(bufferooMapper.mapFromEntity(bufferooEntity))
                .thenReturn(bufferoo)
    }
}