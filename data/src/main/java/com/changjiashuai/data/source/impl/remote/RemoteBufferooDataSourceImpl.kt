package com.changjiashuai.data.source.impl.remote

import com.changjiashuai.data.model.BufferooEntity
import com.changjiashuai.data.source.BufferooDataSource
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Email: changjiashuai@gmail.com
 *
 * Created by CJS on 2017/12/11 14:42.
 */
open class RemoteBufferooDataSourceImpl @Inject constructor(
        private val bufferooRemote: BufferooRemote
) : BufferooDataSource {

    override fun clearBufferoos(): Completable {
        throw UnsupportedOperationException()
    }

    override fun saveBufferoos(bufferoos: List<BufferooEntity>): Completable {
        throw UnsupportedOperationException()
    }

    override fun getBufferoos(): Flowable<List<BufferooEntity>> {
        return bufferooRemote.getBufferoos()
    }

    override fun isCached(): Single<Boolean> {
        throw UnsupportedOperationException()
    }
}