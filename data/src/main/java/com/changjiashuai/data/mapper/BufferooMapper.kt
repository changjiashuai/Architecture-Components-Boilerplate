package com.changjiashuai.data.mapper

import com.changjiashuai.data.model.BufferooEntity
import com.changjiashuai.domain.executor.model.Bufferoo
import javax.inject.Inject

/**
 * Email: changjiashuai@gmail.com
 *
 * Created by CJS on 2017/12/11 14:32.
 */
open class BufferooMapper @Inject constructor() : Mapper<BufferooEntity, Bufferoo> {

    override fun mapFromEntity(type: BufferooEntity): Bufferoo {
        return Bufferoo(type.id, type.name, type.titie, type.avatar)
    }

    override fun mapToEntity(type: Bufferoo): BufferooEntity {
        return BufferooEntity(type.id, type.name, type.title, type.avatar)
    }
}