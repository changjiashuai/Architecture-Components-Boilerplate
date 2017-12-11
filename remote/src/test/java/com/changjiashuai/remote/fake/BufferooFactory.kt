package com.changjiashuai.remote.fake

import com.changjiashuai.remote.BufferooService
import com.changjiashuai.remote.fake.DataFactory.Factory.randomLong
import com.changjiashuai.remote.fake.DataFactory.Factory.randomUuid
import com.changjiashuai.remote.model.BufferooModel

/**
 * Email: changjiashuai@gmail.com
 *
 * Created by CJS on 2017/12/11 17:17.
 */
class BufferooFactory {
    
    companion object Factory {

        fun makeBufferooResponse(): BufferooService.BufferooResponse {
            val bufferooResponse = BufferooService.BufferooResponse()
            bufferooResponse.team = makeBufferooModelList(5)
            return bufferooResponse
        }

        fun makeBufferooModelList(count: Int): List<BufferooModel> {
            val bufferooEntities = mutableListOf<BufferooModel>()
            repeat(count) {
                bufferooEntities.add(makeBufferooModel())
            }
            return bufferooEntities
        }

        fun makeBufferooModel(): BufferooModel {
            return BufferooModel(randomLong(), randomUuid(), randomUuid(), randomUuid())
        }

    }
}