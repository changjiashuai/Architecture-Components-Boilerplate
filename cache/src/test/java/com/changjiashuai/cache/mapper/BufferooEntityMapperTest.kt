package com.changjiashuai.cache.mapper

import com.changjiashuai.cache.fake.BufferooFactory
import com.changjiashuai.cache.model.CachedBufferoo
import com.changjiashuai.data.model.BufferooEntity
import org.junit.Before
import org.junit.Test

import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

/**
 * Email: changjiashuai@gmail.com
 *
 *
 * Created by CJS on 2017/12/11 18:25.
 */
@RunWith(JUnit4::class)
class BufferooEntityMapperTest {

    private lateinit var bufferooEntityMapper: BufferooEntityMapper

    @Before
    fun setUp() {
        bufferooEntityMapper = BufferooEntityMapper()
    }

    @Test
    fun mapFromCached() {
        val cachedBufferoo = BufferooFactory.makeCachedBufferoo()
        val bufferooEntity = bufferooEntityMapper.mapFromCached(cachedBufferoo)

        assertBufferooDataEquality(bufferooEntity, cachedBufferoo)
    }

    @Test
    fun mapToCached() {
        val bufferooEntity = BufferooFactory.makeBufferooEntity()
        val cachedBufferoo = bufferooEntityMapper.mapToCached(bufferooEntity)

        assertBufferooDataEquality(bufferooEntity, cachedBufferoo)
    }

    private fun assertBufferooDataEquality(bufferooEntity: BufferooEntity,
                                           cachedBufferoo: CachedBufferoo) {
        assertEquals(bufferooEntity.name, cachedBufferoo.name)
        assertEquals(bufferooEntity.titie, cachedBufferoo.title)
        assertEquals(bufferooEntity.avatar, cachedBufferoo.avatar)
    }
}