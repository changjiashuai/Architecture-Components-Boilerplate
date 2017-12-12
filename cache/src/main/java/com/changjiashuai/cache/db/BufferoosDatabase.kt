package com.changjiashuai.cache.db

import android.arch.persistence.room.*
import android.content.Context
import com.changjiashuai.cache.dao.CachedBufferooDao
import com.changjiashuai.cache.model.CachedBufferoo
import javax.inject.Inject

/**
 * Email: changjiashuai@gmail.com
 *
 * Created by CJS on 2017/12/11 17:59.
 */
@Database(entities = arrayOf(CachedBufferoo::class), version = 1)
abstract class BufferoosDatabase @Inject constructor() : RoomDatabase() {

    abstract fun cachedBufferooDao(): CachedBufferooDao

    private var INSTANCE: BufferoosDatabase? = null

    private val sLock = Any()

    fun getInstance(context: Context): BufferoosDatabase {
        if (INSTANCE == null) {
            synchronized(sLock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            BufferoosDatabase::class.java, "bufferoos.db").build()
                }
                return INSTANCE!!
            }
        }
        return INSTANCE!!
    }
}