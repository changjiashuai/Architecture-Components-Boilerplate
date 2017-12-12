package com.changjiashuai.presentation.browse

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.changjiashuai.domain.executor.model.Bufferoo
import com.changjiashuai.domain.interactor.browse.GetBufferoos
import com.changjiashuai.presentation.data.ResourceState
import com.changjiashuai.presentation.fake.BufferooFactory
import com.changjiashuai.presentation.fake.DataFactory
import com.changjiashuai.presentation.mapper.BufferooMapper
import com.changjiashuai.presentation.model.BufferooView
import com.nhaarman.mockito_kotlin.*
import io.reactivex.subscribers.DisposableSubscriber
import org.junit.Before
import org.junit.Rule
import org.junit.Test

import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Captor
import org.mockito.Mock

/**
 * Email: changjiashuai@gmail.com
 *
 *
 * Created by CJS on 2017/12/12 10:22.
 */
@RunWith(JUnit4::class)
class BrowseBufferoosViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var getBufferoos: GetBufferoos
    @Mock
    lateinit var bufferooMapper: BufferooMapper

    @Captor
    private lateinit var captor: KArgumentCaptor<DisposableSubscriber<List<Bufferoo>>>

    private lateinit var bufferoosViewModel: BrowseBufferoosViewModel

    @Before
    fun setUp() {
        captor = argumentCaptor()
        getBufferoos = mock()
        bufferooMapper = mock()
        bufferoosViewModel = BrowseBufferoosViewModel(getBufferoos, bufferooMapper)
    }

    @Test
    fun getBufferoosExecutesUseCase() {
        bufferoosViewModel.getBufferoos()
        verify(getBufferoos, times(1)).execute(any(), anyOrNull())
    }

    @Test
    fun getBufferoosReturnSuccess() {
        val list = BufferooFactory.makeBufferooList(2)
        val viewList = BufferooFactory.makeBufferooViewList(2)
        stubBufferooMapperMapToView(viewList[0], list[0])
        stubBufferooMapperMapToView(viewList[1], list[1])

        bufferoosViewModel.getBufferoos()

        verify(getBufferoos).execute(captor.capture(), eq(null))
        captor.firstValue.onNext(list)

        assert(bufferoosViewModel.getBufferoos().value?.status == ResourceState.SUCCESS)
    }

    @Test
    fun getBufferoosReturnDataOnSuccess() {
        val list = BufferooFactory.makeBufferooList(2)
        val viewList = BufferooFactory.makeBufferooViewList(2)

        stubBufferooMapperMapToView(viewList[0], list[0])
        stubBufferooMapperMapToView(viewList[1], list[1])

        bufferoosViewModel.getBufferoos()

        verify(getBufferoos).execute(captor.capture(), eq(null))
        captor.firstValue.onNext(list)

        assert(bufferoosViewModel.getBufferoos().value?.data == viewList)
    }

    @Test
    fun getBufferoosReturnNoMessageOnSuccess() {
        val list = BufferooFactory.makeBufferooList(2)
        val viewList = BufferooFactory.makeBufferooViewList(2)

        stubBufferooMapperMapToView(viewList[0], list[0])
        stubBufferooMapperMapToView(viewList[1], list[1])

        bufferoosViewModel.getBufferoos()

        verify(getBufferoos).execute(captor.capture(), eq(null))
        captor.firstValue.onNext(list)

        assert(bufferoosViewModel.getBufferoos().value?.message == null)
    }

    @Test
    fun getBufferoosReturnError() {
        bufferoosViewModel.getBufferoos()

        verify(getBufferoos).execute(captor.capture(), eq(null))
        captor.firstValue.onError(RuntimeException())

        assert(bufferoosViewModel.getBufferoos().value?.status == ResourceState.ERROR)
    }

    @Test
    fun getBufferoosFailsAndContainsNoData() {
        bufferoosViewModel.getBufferoos()

        verify(getBufferoos).execute(captor.capture(), eq(null))
        captor.firstValue.onError(RuntimeException())

        assert(bufferoosViewModel.getBufferoos().value?.data == null)
    }

    @Test
    fun getBufferoosFailsAndContainsMessage() {
        val errorMessage = DataFactory.randomUuid()
        bufferoosViewModel.getBufferoos()

        verify(getBufferoos).execute(captor.capture(), eq(null))
        captor.firstValue.onError(RuntimeException(errorMessage))

        assert(bufferoosViewModel.getBufferoos().value?.message == errorMessage)
    }

    @Test
    fun getBufferoosReturnLoading() {
        bufferoosViewModel.getBufferoos()

        assert(bufferoosViewModel.getBufferoos().value?.status == ResourceState.LOADING)
    }

    @Test
    fun getBufferoosContainsNoDataWhenLoading() {
        bufferoosViewModel.getBufferoos()

        assert(bufferoosViewModel.getBufferoos().value?.data == null)
    }

    @Test
    fun getBufferoosContainsNoMessageWhenLoading() {
        bufferoosViewModel.getBufferoos()

        assert(bufferoosViewModel.getBufferoos().value?.data == null)
    }

    private fun stubBufferooMapperMapToView(bufferooView: BufferooView, bufferoo: Bufferoo) {
        whenever(bufferooMapper.mapToView(bufferoo))
                .thenReturn(bufferooView)
    }
}