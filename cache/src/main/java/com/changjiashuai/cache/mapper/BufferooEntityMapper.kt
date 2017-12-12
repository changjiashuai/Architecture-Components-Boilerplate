package com.changjiashuai.cache.mapper

import com.changjiashuai.cache.model.CachedBufferoo
import com.changjiashuai.data.model.BufferooEntity
import javax.inject.Inject

/**
 * Email: changjiashuai@gmail.com
 *
 * Created by CJS on 2017/12/11 18:11.
 */
open class BufferooEntityMapper @Inject constructor() : EntityMapper<CachedBufferoo, BufferooEntity> {

    override fun mapFromCached(type: CachedBufferoo): BufferooEntity {
        return BufferooEntity(type.id, type.name, type.title, type.avatar)
    }

    override fun mapToCached(type: BufferooEntity): CachedBufferoo {
        return CachedBufferoo(type.id, type.name, type.titie, type.avatar)
    }
}