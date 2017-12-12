package com.changjiashuai.cache.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.changjiashuai.cache.db.constants.BufferooConstants
import com.changjiashuai.cache.model.CachedBufferoo

/**
 * Email: changjiashuai@gmail.com
 *
 * Created by CJS on 2017/12/11 18:05.
 */
@Dao
abstract class CachedBufferooDao {

    @Query(BufferooConstants.QUERY_BUFFEROOS)
    abstract fun getBufferoos(): List<CachedBufferoo>

    @Query(BufferooConstants.DELETE_ALL_BUFFEROOS)
    abstract fun clearBufferoos()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertBufferoo(cachedBufferoo: CachedBufferoo)
}