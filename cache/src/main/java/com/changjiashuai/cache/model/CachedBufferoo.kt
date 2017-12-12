package com.changjiashuai.cache.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.changjiashuai.cache.db.constants.BufferooConstants

/**
 * Email: changjiashuai@gmail.com
 *
 * Created by CJS on 2017/12/11 18:00.
 */
@Entity(tableName = BufferooConstants.TABLE_NAME)
data class CachedBufferoo(

        @PrimaryKey
        var id: Long,
        val name: String,
        val title: String,
        val avatar: String
)