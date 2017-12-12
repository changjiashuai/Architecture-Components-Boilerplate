package com.changjiashuai.cache.fake

import com.changjiashuai.cache.fake.DataFactory.Factory.randomLong
import com.changjiashuai.cache.fake.DataFactory.Factory.randomUuid
import com.changjiashuai.cache.model.CachedBufferoo
import com.changjiashuai.data.model.BufferooEntity

/**
 * Email: changjiashuai@gmail.com
 *
 * Created by CJS on 2017/12/11 18:28.
 */
class BufferooFactory {

    companion object Factory {

        fun makeCachedBufferoo(): CachedBufferoo {
            return CachedBufferoo(randomLong(), randomUuid(), randomUuid(), randomUuid())
        }

        fun makeBufferooEntity(): BufferooEntity {
            return BufferooEntity(randomLong(), randomUuid(), randomUuid(), randomUuid())
        }

        fun makeBufferooEntityList(count: Int): List<BufferooEntity> {
            val bufferooEntities = mutableListOf<BufferooEntity>()
            repeat(count) {
                bufferooEntities.add(makeBufferooEntity())
            }
            return bufferooEntities
        }

        fun makeCachedBufferooList(count: Int): List<CachedBufferoo> {
            val cachedBufferoos = mutableListOf<CachedBufferoo>()
            repeat(count) {
                cachedBufferoos.add(makeCachedBufferoo())
            }
            return cachedBufferoos
        }

    }
}