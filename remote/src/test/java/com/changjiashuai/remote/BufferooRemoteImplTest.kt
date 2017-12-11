package com.changjiashuai.remote

import com.changjiashuai.data.model.BufferooEntity
import com.changjiashuai.remote.fake.BufferooFactory
import com.changjiashuai.remote.mapper.BufferooEntityMapper
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test

import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Email: changjiashuai@gmail.com
 *
 *
 * Created by CJS on 2017/12/11 17:15.
 */
@RunWith(JUnit4::class)
class BufferooRemoteImplTest {

    private lateinit var entityMapper: BufferooEntityMapper
    private lateinit var bufferooService: BufferooService
    private lateinit var bufferooRemoteImpl: BufferooRemoteImpl

    @Before
    fun setUp() {
        entityMapper = mock()
        bufferooService = mock()
        bufferooRemoteImpl = BufferooRemoteImpl(bufferooService, entityMapper)
    }

    @Test
    fun getBufferoosCompletes() {
        stubBufferooServiceGetBufferoos(Flowable.just(BufferooFactory.makeBufferooResponse()))
        val testObserver = bufferooRemoteImpl.getBufferoos().test()
        testObserver.assertComplete()
    }

    @Test
    fun getBufferoosReturnData() {
        val bufferooResponse = BufferooFactory.makeBufferooResponse()
        stubBufferooServiceGetBufferoos(Flowable.just(bufferooResponse))
        val bufferooEntities = mutableListOf<BufferooEntity>()
        bufferooResponse.team.forEach { bufferooEntities.add(entityMapper.mapFromRemote(it)) }

        val testObserver = bufferooRemoteImpl.getBufferoos().test()
        testObserver.assertValue(bufferooEntities)
    }

    private fun stubBufferooServiceGetBufferoos(observable: Flowable<BufferooService.BufferooResponse>) {
        whenever(bufferooService.getBufferoos())
                .thenReturn(observable)
    }
}