package com.changjiashuai.domain.interactor.browse

import com.changjiashuai.domain.executor.PostExecutionThread
import com.changjiashuai.domain.executor.ThreadExecutor
import com.changjiashuai.domain.repository.BufferooRepository
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.After
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
    fun getBufferooRepository() {
    }

    @After
    fun tearDown() {
    }
}