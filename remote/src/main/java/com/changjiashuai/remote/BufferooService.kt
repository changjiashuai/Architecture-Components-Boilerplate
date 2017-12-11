package com.changjiashuai.remote

import com.changjiashuai.remote.model.BufferooModel
import io.reactivex.Flowable
import retrofit2.http.GET

/**
 * Email: changjiashuai@gmail.com
 *
 * Created by CJS on 2017/12/11 16:56.
 */
interface BufferooService {

    @GET("team.json")
    fun getBufferoos(): Flowable<BufferooResponse>

    class BufferooResponse {
        lateinit var team: List<BufferooModel>
    }
}