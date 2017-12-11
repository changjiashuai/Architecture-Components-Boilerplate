package com.changjiashuai.domain.interactor.browse.fake

import java.util.*
import java.util.concurrent.ThreadLocalRandom

/**
 * Email: changjiashuai@gmail.com
 *
 * Created by CJS on 2017/12/11 11:51.
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
            return UUID.randomUUID().toString()
        }
    }
}