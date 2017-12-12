package com.changjiashuai.presentation.fake

import com.changjiashuai.domain.executor.model.Bufferoo
import com.changjiashuai.presentation.fake.DataFactory.Factory.randomLong
import com.changjiashuai.presentation.fake.DataFactory.Factory.randomUuid
import com.changjiashuai.presentation.model.BufferooView

/**
 * Email: changjiashuai@gmail.com
 *
 * Created by CJS on 2017/12/12 10:23.
 */
class BufferooFactory {

    companion object Factory {

        fun makeBufferooList(count: Int): List<Bufferoo> {
            val bufferoos = mutableListOf<Bufferoo>()
            repeat(count) {
                bufferoos.add(makeBufferooModel())
            }
            return bufferoos
        }

        fun makeBufferooModel(): Bufferoo {
            return Bufferoo(randomLong(), randomUuid(), randomUuid(), randomUuid())
        }

        fun makeBufferooViewList(count: Int): List<BufferooView> {
            val bufferoos = mutableListOf<BufferooView>()
            repeat(count) {
                bufferoos.add(makeBufferooView())
            }
            return bufferoos
        }

        fun makeBufferooView(): BufferooView {
            return BufferooView(randomUuid(), randomUuid(), randomUuid())
        }

    }
}