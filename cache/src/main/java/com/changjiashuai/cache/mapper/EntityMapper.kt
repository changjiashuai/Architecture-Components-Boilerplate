package com.changjiashuai.cache.mapper

/**
 * Email: changjiashuai@gmail.com
 *
 * Created by CJS on 2017/12/11 18:10.
 */
interface EntityMapper<T, V> {

    fun mapFromCached(type: T): V
    fun mapToCached(type: V): T
}