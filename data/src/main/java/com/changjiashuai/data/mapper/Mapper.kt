package com.changjiashuai.data.mapper

/**
 * Email: changjiashuai@gmail.com
 *
 * Created by CJS on 2017/12/11 14:31.
 */
interface Mapper<E, D> {

    fun mapFromEntity(type: E): D
    fun mapToEntity(type: D): E
}