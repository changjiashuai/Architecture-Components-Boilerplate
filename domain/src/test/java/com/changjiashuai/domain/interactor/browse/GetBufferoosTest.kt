package com.changjiashuai.domain.interactor.browse

import com.changjiashuai.domain.executor.PostExecutionThread
import com.changjiashuai.domain.executor.ThreadExecutor
import com.changjiashuai.domain.executor.model.Bufferoo
import com.changjiashuai.domain.interactor.browse.fake.BufferooFactory
import com.changjiashuai.domain.repository.BufferooRepository
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test

/**
 * Email: changjiashuai@gmail.com
 *
 * Created by CJS on 2017/12/11 11:28.
 */
class GetBufferoosTest {

    private lateinit var mockThreadExecutor: ThreadExecutor
    private lateinit var mockPostExectionThread: PostExecutionThread
    private lateinit var mockBufferooRepository: BufferooRepository

    private lateinit var getBufferoos: GetBufferoos


    @Before
    fun setUp() {
        mockThreadExecutor = mock()
        mockPostExectionThread = mock()
        mockBufferooRepository = mock()

        getBufferoos = GetBufferoos(mockBufferooRepository, mockThreadExecutor, mockPostExectionThread)
    }

    @Test
    fun buildUseCaseObservableCallByRepository() {
        getBufferoos.buildUseCaseObservable()
        //校验方法是否调用
        verify(mockBufferooRepository).getBufferoos()
    }

    @Test
    fun buildUseCaseObservableCompletes() {
        stubBufferooRepositoryGetBufferoos(Flowable.just(BufferooFactory.makeBufferooList(2)))
        val testObserver = getBufferoos.buildUseCaseObservable().test()
        testObserver.assertComplete()
    }

    @Test
    fun buildUseCaseObservableReturnData(){
        val bufferoos = BufferooFactory.makeBufferooList(2)
        stubBufferooRepositoryGetBufferoos(Flowable.just(bufferoos))
        val testObserver = getBufferoos.buildUseCaseObservable().test()
        testObserver.assertValue(bufferoos)
    }

    //打桩
    private fun stubBufferooRepositoryGetBufferoos(single: Flowable<List<Bufferoo>>) {
        whenever(mockBufferooRepository.getBufferoos())
                .thenReturn(single)
    }
}