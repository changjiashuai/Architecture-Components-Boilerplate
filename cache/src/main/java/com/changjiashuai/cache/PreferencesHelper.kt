package com.changjiashuai.cache

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Email: changjiashuai@gmail.com
 *
 * Created by CJS on 2017/12/11 18:13.
 */
@Singleton
open class PreferencesHelper @Inject constructor(context: Context) {

    companion object {
        private val PREF_BUFFER_PACKAGE_NAME = "com.changjiashuai.boilerplate.preferences"
        private val PREF_KEY_LAST_CACHE = "last_cache"
    }

    private val bufferPref: SharedPreferences

    init {
        bufferPref = context.getSharedPreferences(PREF_BUFFER_PACKAGE_NAME, Context.MODE_PRIVATE)
    }

    var lastCacheTime: Long
        get() = bufferPref.getLong(PREF_KEY_LAST_CACHE, 0)
        set(value) = bufferPref.edit().putLong(PREF_KEY_LAST_CACHE, value).apply()
}