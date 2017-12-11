package com.changjiashuai.domain.interactor.browse.fake

import com.changjiashuai.domain.executor.model.Bufferoo

/**
 * Email: changjiashuai@gmail.com
 *
 * Created by CJS on 2017/12/11 11:49.
 */
class BufferooFactory {

    companion object Factory {

        fun makeBufferooList(count: Int): List<Bufferoo> {
            val bufferoos = mutableListOf<Bufferoo>()
            repeat(count) {
                bufferoos.add(makeBufferoo())
            }
            return bufferoos
        }

        fun makeBufferoo(): Bufferoo {
            return Bufferoo(DataFactory.randomLong(), DataFactory.randomUuid(), DataFactory.randomUuid(), DataFactory.randomUuid())
        }
    }
}