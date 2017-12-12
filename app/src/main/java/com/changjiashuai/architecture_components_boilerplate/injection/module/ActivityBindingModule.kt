package com.changjiashuai.architecture_components_boilerplate.injection.module

import com.changjiashuai.architecture_components_boilerplate.browse.MainActivity
import com.changjiashuai.architecture_components_boilerplate.injection.scopes.PerActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Email: changjiashuai@gmail.com
 *
 * Created by CJS on 2017/12/12 15:02.
 */
@Module
abstract class ActivityBindingModule {

    @PerActivity
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun bindMainActivity(): MainActivity
}