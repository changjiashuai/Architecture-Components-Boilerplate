package com.changjiashuai.architecture_components_boilerplate.injection

import android.app.Application
import com.changjiashuai.architecture_components_boilerplate.App
import com.changjiashuai.architecture_components_boilerplate.injection.module.ActivityBindingModule
import com.changjiashuai.architecture_components_boilerplate.injection.module.AppModule
import com.changjiashuai.architecture_components_boilerplate.injection.scopes.PerApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

/**
 * Email: changjiashuai@gmail.com
 *
 * Created by CJS on 2017/12/12 14:47.
 */
@PerApplication
@Component(modules = [ActivityBindingModule::class, AppModule::class, AndroidSupportInjectionModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)
}