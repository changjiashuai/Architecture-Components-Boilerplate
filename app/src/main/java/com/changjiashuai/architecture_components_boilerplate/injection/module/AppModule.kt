package com.changjiashuai.architecture_components_boilerplate.injection.module

import android.app.Application
import android.arch.lifecycle.ViewModelProvider
import android.arch.persistence.room.Room
import android.content.Context
import com.changjiashuai.architecture_components_boilerplate.BuildConfig
import com.changjiashuai.architecture_components_boilerplate.UIThread
import com.changjiashuai.architecture_components_boilerplate.injection.scopes.PerApplication
import com.changjiashuai.cache.BufferooCacheImpl
import com.changjiashuai.cache.PreferencesHelper
import com.changjiashuai.cache.db.BufferoosDatabase
import com.changjiashuai.cache.mapper.BufferooEntityMapper
import com.changjiashuai.data.executor.JobExecutor
import com.changjiashuai.data.mapper.BufferooMapper
import com.changjiashuai.data.repository.BufferooDataRepository
import com.changjiashuai.data.source.BufferooDataSourceFactory
import com.changjiashuai.data.source.cache.BufferooCache
import com.changjiashuai.data.source.remote.BufferooRemote
import com.changjiashuai.domain.executor.PostExecutionThread
import com.changjiashuai.domain.executor.ThreadExecutor
import com.changjiashuai.domain.repository.BufferooRepository
import com.changjiashuai.remote.BufferooRemoteImpl
import com.changjiashuai.remote.BufferooService
import com.changjiashuai.remote.BufferooServiceFactory
import dagger.Module
import dagger.Provides

/**
 * Email: changjiashuai@gmail.com
 *
 * Created by CJS on 2017/12/12 14:50.
 */
@Module
open class AppModule {

    @Provides
    @PerApplication
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @PerApplication
    internal fun providePreferencesHelper(context: Context): PreferencesHelper {
        return PreferencesHelper(context)
    }

    @Provides
    @PerApplication
    internal fun provideBufferooRepository(factory: BufferooDataSourceFactory,
                                           mapper: BufferooMapper): BufferooRepository {
        return BufferooDataRepository(factory, mapper)
    }

    @Provides
    @PerApplication
    internal fun provideBufferooCache(database: BufferoosDatabase,
                                      entityMapper: BufferooEntityMapper,
                                      helper: PreferencesHelper): BufferooCache {
        return BufferooCacheImpl(database, entityMapper, helper)
    }

    @Provides
    @PerApplication
    internal fun provideBufferooRemote(service: BufferooService,
                                       entityMapper: com.changjiashuai.remote.mapper.BufferooEntityMapper): BufferooRemote {
        return BufferooRemoteImpl(service, entityMapper)
    }

    @Provides
    @PerApplication
    internal fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor {
        return jobExecutor
    }

    @Provides
    @PerApplication
    internal fun providePostExecutionThread(uiThread: UIThread): PostExecutionThread {
        return uiThread
    }

    @Provides
    @PerApplication
    internal fun provideBufferooService(): BufferooService {
        return BufferooServiceFactory.makeBufferooService(BuildConfig.DEBUG)
    }

    @Provides
    @PerApplication
    internal fun provideViewModelFactory(): ViewModelProvider.Factory {
        return ViewModelProvider.NewInstanceFactory()
    }

    @Provides
    @PerApplication
    internal fun provideBufferoosDatabase(application: Application): BufferoosDatabase {
        return Room.databaseBuilder(application.applicationContext,
                BufferoosDatabase::class.java, "bufferoos.db")
                .build()
    }
}