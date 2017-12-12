package com.changjiashuai.presentation.data

/**
 * Email: changjiashuai@gmail.com
 *
 * Created by CJS on 2017/12/12 10:10.
 */
open class Resource<out T> constructor(val status: ResourceState, val data: T?, val message: String?) {

    fun <T> success(data: T): Resource<T> {
        return Resource(ResourceState.SUCCESS, data, null)
    }

    fun <T> error(message: String, data: T? = null): Resource<T> {
        return Resource(ResourceState.ERROR, data, message)
    }

    fun <T> loading(): Resource<T> {
        return Resource(ResourceState.LOADING, null, null)
    }
}