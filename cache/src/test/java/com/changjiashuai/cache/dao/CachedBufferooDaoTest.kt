package com.changjiashuai.cache.dao

import android.arch.persistence.room.Room
import com.changjiashuai.cache.db.BufferoosDatabase
import com.changjiashuai.cache.fake.BufferooFactory
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

/**
 * Email: changjiashuai@gmail.com
 *
 *
 * Created by CJS on 2017/12/11 18:32.
 */
@RunWith(RobolectricTestRunner::class)
class CachedBufferooDaoTest {

    private lateinit var bufferoosDatabase: BufferoosDatabase

    @Before
    fun setUp() {
        bufferoosDatabase = Room.inMemoryDatabaseBuilder(
                RuntimeEnvironment.application.baseContext,
                BufferoosDatabase::class.java
        ).allowMainThreadQueries().build()
    }

    @After
    fun tearDown() {
        bufferoosDatabase.close()
    }

    @Test
    fun insertBufferoosSavesData() {
        val cachedBufferoo = BufferooFactory.makeCachedBufferoo()
        bufferoosDatabase.cachedBufferooDao().insertBufferoo(cachedBufferoo)

        val bufferoos = bufferoosDatabase.cachedBufferooDao().getBufferoos()
        assert(bufferoos.isNotEmpty())
    }

    @Test
    fun getBufferoosRetrieveData() {
        val cachedBufferoos = BufferooFactory.makeCachedBufferooList(5)
        cachedBufferoos.forEach { bufferoosDatabase.cachedBufferooDao().insertBufferoo(it) }

        val retrievedBufferoos = bufferoosDatabase.cachedBufferooDao().getBufferoos()
        assert(retrievedBufferoos == cachedBufferoos.sortedWith(compareBy({ it.id }, { it.id })))
    }
}