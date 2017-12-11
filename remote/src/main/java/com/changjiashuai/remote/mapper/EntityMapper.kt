package com.changjiashuai.remote.mapper

/**
 * Email: changjiashuai@gmail.com
 *
 * Created by CJS on 2017/12/11 16:57.
 */
interface EntityMapper<in M, out E> {
    fun mapFromRemote(type: M): E
}