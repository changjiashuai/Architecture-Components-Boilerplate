package com.changjiashuai.remote.mapper

import com.changjiashuai.data.model.BufferooEntity
import com.changjiashuai.remote.model.BufferooModel
import javax.inject.Inject

/**
 * Email: changjiashuai@gmail.com
 *
 * Created by CJS on 2017/12/11 16:58.
 */
open class BufferooEntityMapper @Inject constructor() : EntityMapper<BufferooModel, BufferooEntity> {

    override fun mapFromRemote(type: BufferooModel): BufferooEntity {
        return BufferooEntity(type.id, type.name, type.title, type.avatar)
    }
}