package com.changjiashuai.data.source.remote

import com.changjiashuai.data.fake.BufferooFactory
import com.changjiashuai.data.model.BufferooEntity
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
 * Created by CJS on 2017/12/11 16:43.
 */
@RunWith(JUnit4::class)
class RemoteBufferooDataSourceTest {

    private lateinit var bufferooRemote: BufferooRemote
    private lateinit var remoteBufferooDataSource: RemoteBufferooDataSource

    @Before
    fun setUp() {
        bufferooRemote = mock()
        remoteBufferooDataSource = RemoteBufferooDataSource(bufferooRemote)
    }

    @Test(expected = UnsupportedOperationException::class)
    fun clearBufferoos() {
        remoteBufferooDataSource.clearBufferoos().test()
    }

    @Test(expected = UnsupportedOperationException::class)
    fun saveBufferoos() {
        remoteBufferooDataSource.saveBufferoos(BufferooFactory.makeBufferooEntityList(2)).test()
    }

    @Test
    fun getBufferoosCompletes() {
        stubBufferooCacheGetBufferoos(Flowable.just(BufferooFactory.makeBufferooEntityList(2)))
        val testObserver = bufferooRemote.getBufferoos().test()
        testObserver.assertComplete()
    }

    private fun stubBufferooCacheGetBufferoos(single: Flowable<List<BufferooEntity>>) {
        whenever(bufferooRemote.getBufferoos())
                .thenReturn(single)
    }
}