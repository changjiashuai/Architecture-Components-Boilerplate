package com.changjiashuai.presentation.mapper

/**
 * Email: changjiashuai@gmail.com
 *
 * Created by CJS on 2017/12/12 10:07.
 */
interface Mapper<out V, in D> {

    fun mapToView(type: D): V
}