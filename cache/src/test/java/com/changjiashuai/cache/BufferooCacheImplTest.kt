package com.changjiashuai.cache

import android.arch.persistence.room.Room
import com.changjiashuai.cache.db.BufferoosDatabase
import com.changjiashuai.cache.fake.BufferooFactory
import com.changjiashuai.cache.mapper.BufferooEntityMapper
import com.changjiashuai.cache.model.CachedBufferoo
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

/**
 * Email: changjiashuai@gmail.com
 *
 *
 * Created by CJS on 2017/12/12 09:47.
 */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [21])
class BufferooCacheImplTest {

    private var bufferoosDatabase = Room.inMemoryDatabaseBuilder(RuntimeEnvironment.application,
            BufferoosDatabase::class.java).allowMainThreadQueries().build()
    private var entityMapper = BufferooEntityMapper()
    private var preferencesHelper = PreferencesHelper(RuntimeEnvironment.application)

    private val databaseHelper = BufferooCacheImpl(bufferoosDatabase, entityMapper, preferencesHelper)

    @Before
    fun setUp() {
    }

    @Test
    fun clearTablesCompletes() {
        val testObserver = databaseHelper.clearBufferoos().test()
        testObserver.assertComplete()
    }

    @Test
    fun saveBufferoosCompletes() {
        val bufferooEntities = BufferooFactory.makeBufferooEntityList(2)
        val testObserver = databaseHelper.saveBufferoos(bufferooEntities).test()
        testObserver.assertComplete()
    }

    @Test
    fun saveBufferoosSavesData() {
        val bufferooCount = 2
        val bufferooEntities = BufferooFactory.makeBufferooEntityList(bufferooCount)
        databaseHelper.saveBufferoos(bufferooEntities).test()
        checkNumRowsInBufferoosTable(bufferooCount)
    }

    @Test
    fun getBufferoosCompletes() {
        val testObserver = databaseHelper.getBufferoos().test()
        testObserver.assertComplete()
    }

    @Test
    fun getBufferoosReturnData() {
        val bufferooEntities = BufferooFactory.makeBufferooEntityList(2)
        val cachedBufferoos = mutableListOf<CachedBufferoo>()
        bufferooEntities.forEach { cachedBufferoos.add(entityMapper.mapToCached(it)) }
        insertBufferoos(cachedBufferoos)
        val testObserver = databaseHelper.getBufferoos().test()
        testObserver.assertValue(bufferooEntities)
    }

    private fun insertBufferoos(cachedBufferoos: List<CachedBufferoo>) {
        cachedBufferoos.forEach { bufferoosDatabase.cachedBufferooDao().insertBufferoo(it) }
    }

    private fun checkNumRowsInBufferoosTable(expectedRows: Int) {
        val numberOfRows = bufferoosDatabase.cachedBufferooDao().getBufferoos().size
        assertEquals(expectedRows, numberOfRows)
    }
}