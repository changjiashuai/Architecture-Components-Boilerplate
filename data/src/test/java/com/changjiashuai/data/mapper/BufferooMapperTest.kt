package com.changjiashuai.data.mapper

import com.changjiashuai.data.mapper.fake.BufferooFactory
import com.changjiashuai.data.model.BufferooEntity
import com.changjiashuai.domain.executor.model.Bufferoo
import org.junit.Before
import org.junit.Test

import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

/**
 * Email: changjiashuai@gmail.com
 *
 * Created by CJS on 2017/12/11 15:17.
 */
@RunWith(JUnit4::class)
class BufferooMapperTest {

    private lateinit var bufferooMapper: BufferooMapper

    @Before
    fun setUp() {
        bufferooMapper = BufferooMapper()
    }

    @Test
    fun mapFromEntity() {
        val bufferooEntity = BufferooFactory.makeBufferooEntity()
        val bufferoo = bufferooMapper.mapFromEntity(bufferooEntity)

        assertBufferooDataEquality(bufferooEntity, bufferoo)
    }

    @Test
    fun mapToEntity() {
        val cachedBufferoo = BufferooFactory.makeBufferoo()
        val bufferooEntity = bufferooMapper.mapToEntity(cachedBufferoo)

        assertBufferooDataEquality(bufferooEntity, cachedBufferoo)
    }

    private fun assertBufferooDataEquality(bufferooEntity: BufferooEntity,
                                           bufferoo: Bufferoo) {
        assertEquals(bufferooEntity.name, bufferoo.name)
        assertEquals(bufferooEntity.titie, bufferoo.title)
        assertEquals(bufferooEntity.avatar, bufferoo.avatar)
    }
}