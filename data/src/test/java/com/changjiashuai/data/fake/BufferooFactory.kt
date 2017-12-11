package com.changjiashuai.data.fake

import com.changjiashuai.data.model.BufferooEntity
import com.changjiashuai.data.fake.DataFactory.Factory.randomLong
import com.changjiashuai.data.fake.DataFactory.Factory.randomUuid
import com.changjiashuai.domain.executor.model.Bufferoo

/**
 * Email: changjiashuai@gmail.com
 *
 * Created by CJS on 2017/12/11 15:23.
 */
class BufferooFactory {

    companion object Factory {

        fun makeBufferooEntity(): BufferooEntity {
            return BufferooEntity(randomLong(), randomUuid(), randomUuid(), randomUuid())
        }

        fun makeBufferoo(): Bufferoo {
            return Bufferoo(randomLong(), randomUuid(), randomUuid(), randomUuid())
        }

        fun makeBufferooEntityList(count: Int): List<BufferooEntity> {
            val bufferooEntities = mutableListOf<BufferooEntity>()
            repeat(count) {
                bufferooEntities.add(makeBufferooEntity())
            }
            return bufferooEntities
        }

        fun makeBufferooList(count: Int): List<Bufferoo> {
            val bufferoos = mutableListOf<Bufferoo>()
            repeat(count) {
                bufferoos.add(makeBufferoo())
            }
            return bufferoos
        }
    }
}