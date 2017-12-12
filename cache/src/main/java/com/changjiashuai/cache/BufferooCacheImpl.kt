package com.changjiashuai.cache

import com.changjiashuai.cache.db.BufferoosDatabase
import com.changjiashuai.cache.mapper.BufferooEntityMapper
import com.changjiashuai.data.model.BufferooEntity
import com.changjiashuai.data.source.cache.BufferooCache
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Email: changjiashuai@gmail.com
 *
 * Created by CJS on 2017/12/11 17:56.
 */
class BufferooCacheImpl @Inject constructor(
        val bufferoosDatabase: BufferoosDatabase,
        private val entityMapper: BufferooEntityMapper,
        private val preferencesHelper: PreferencesHelper
) : BufferooCache {

    private val EXPIRATION_TIME = (60 * 10 * 1000).toLong()

    internal fun getDatabase(): BufferoosDatabase {
        return bufferoosDatabase
    }

    override fun clearBufferoos(): Completable {
        return Completable.defer {
            bufferoosDatabase.cachedBufferooDao().clearBufferoos()
            Completable.complete()
        }
    }

    override fun saveBufferoos(bufferoos: List<BufferooEntity>): Completable {
        return Completable.defer {
            bufferoos.forEach {
                bufferoosDatabase.cachedBufferooDao().insertBufferoo(entityMapper.mapToCached(it))
            }
            Completable.complete()
        }
    }

    override fun getBufferoos(): Flowable<List<BufferooEntity>> {
        return Flowable.defer {
            Flowable.just(bufferoosDatabase.cachedBufferooDao().getBufferoos())
        }.map { it.map { entityMapper.mapFromCached(it) } }
    }

    override fun isCached(): Single<Boolean> {
        return Single.defer { Single.just(bufferoosDatabase.cachedBufferooDao().getBufferoos().isNotEmpty()) }
    }

    override fun setLastCacheTime(lastCache: Long) {
        preferencesHelper.lastCacheTime = lastCache
    }

    override fun isExpired(): Boolean {
        val currentTime = System.currentTimeMillis()
        val lastUpdateTime = getLastCacheUpdateTimeMillis()
        return currentTime - lastUpdateTime > EXPIRATION_TIME
    }

    private fun getLastCacheUpdateTimeMillis(): Long {
        return preferencesHelper.lastCacheTime
    }
}