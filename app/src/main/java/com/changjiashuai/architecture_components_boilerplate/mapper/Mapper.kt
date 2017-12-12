package com.changjiashuai.architecture_components_boilerplate.mapper

/**
 * Email: changjiashuai@gmail.com
 *
 * Created by CJS on 2017/12/12 14:37.
 */
interface Mapper<out V, in D> {

    fun mapToViewModel(type: D): V
}