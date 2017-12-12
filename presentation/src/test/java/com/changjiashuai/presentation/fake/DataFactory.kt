package com.changjiashuai.presentation.fake

import java.util.concurrent.ThreadLocalRandom

/**
 * Email: changjiashuai@gmail.com
 *
 * Created by CJS on 2017/12/12 10:23.
 */
class DataFactory {

    companion object Factory {

        fun randomInt(): Int {
            return ThreadLocalRandom.current().nextInt(0, 1000 + 1)
        }

        fun randomLong(): Long {
            return randomInt().toLong()
        }

        fun randomUuid(): String {
            return java.util.UUID.randomUUID().toString()
        }

    }
}