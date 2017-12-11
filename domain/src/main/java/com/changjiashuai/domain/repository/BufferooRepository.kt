package com.changjiashuai.domain.repository

import com.changjiashuai.domain.executor.model.Bufferoo
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * Email: changjiashuai@gmail.com
 *
 * Created by CJS on 2017/12/11 11:13.
 *
 * 接口定义数据层如何将数据传入和传出领域层的方法。
 * 这是由数据层实现的，定义了需要实现的操作
 */
interface BufferooRepository {

    fun clearBufferoos(): Completable
    fun saveBufferoos(bufferoos: List<Bufferoo>): Completable
    fun getBufferoos(): Flowable<List<Bufferoo>>
}