package com.changjiashuai.remote.mapper

import com.changjiashuai.remote.fake.BufferooFactory
import org.junit.Before
import org.junit.Test

import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

/**
 * Email: changjiashuai@gmail.com
 *
 *
 * Created by CJS on 2017/12/11 17:15.
 */
@RunWith(JUnit4::class)
class BufferooEntityMapperTest {

    private lateinit var bufferooEntityMapper: BufferooEntityMapper

    @Before
    fun setUp() {
        bufferooEntityMapper = BufferooEntityMapper()
    }

    @Test
    fun mapFromRemote() {
        val bufferooModel = BufferooFactory.makeBufferooModel()
        val bufferooEntity = bufferooEntityMapper.mapFromRemote(bufferooModel)

        assertEquals(bufferooModel.name, bufferooEntity.name)
        assertEquals(bufferooModel.title, bufferooEntity.titie)
        assertEquals(bufferooModel.avatar, bufferooEntity.avatar)
    }

}