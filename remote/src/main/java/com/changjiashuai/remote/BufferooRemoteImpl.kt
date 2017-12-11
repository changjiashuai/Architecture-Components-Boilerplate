package com.changjiashuai.remote

import com.changjiashuai.data.model.BufferooEntity
import com.changjiashuai.data.source.remote.BufferooRemote
import com.changjiashuai.remote.mapper.BufferooEntityMapper
import io.reactivex.Flowable

/**
 * Email: changjiashuai@gmail.com
 *
 * Created by CJS on 2017/12/11 17:02.
 */
class BufferooRemoteImpl constructor(
        private val bufferooService: BufferooService,
        private val entityMapper: BufferooEntityMapper
) : BufferooRemote {

    override fun getBufferoos(): Flowable<List<BufferooEntity>> {
        return bufferooService.getBufferoos()
                .map { it.team }
                .map {
                    val entities = mutableListOf<BufferooEntity>()
                    it.forEach { entities.add(entityMapper.mapFromRemote(it)) }
                    entities
                }
    }
}