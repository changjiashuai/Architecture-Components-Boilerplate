package com.changjiashuai.data.repository

import com.changjiashuai.data.mapper.BufferooMapper
import com.changjiashuai.data.model.BufferooEntity
import com.changjiashuai.data.source.BufferooDataSourceFactory
import com.changjiashuai.domain.executor.model.Bufferoo
import com.changjiashuai.domain.repository.BufferooRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Email: changjiashuai@gmail.com
 *
 * Created by CJS on 2017/12/11 15:03.
 */
class BufferooDataRepository @Inject constructor(
        private val factory: BufferooDataSourceFactory,
        private val bufferooMapper: BufferooMapper
) : BufferooRepository {

    override fun clearBufferoos(): Completable {
        return factory.retrieveCacheDataSource().clearBufferoos()
    }

    override fun saveBufferoos(bufferoos: List<Bufferoo>): Completable {
        val bufferooEntities = mutableListOf<BufferooEntity>()
        bufferoos.map { bufferooEntities.add(bufferooMapper.mapToEntity(it)) }
        return factory.retrieveCacheDataSource().saveBufferoos(bufferooEntities)
    }

    override fun getBufferoos(): Flowable<List<Bufferoo>> {
        return factory.retrieveCacheDataSource().isCached()
                .flatMapPublisher { factory.retrieveDataSource(it).getBufferoos() }
                .flatMap { Flowable.just(it.map { bufferooMapper.mapFromEntity(it) }) }
                .flatMap { saveBufferoos(it).toSingle { it }.toFlowable() }
    }
}