package com.changjiashuai.architecture_components_boilerplate.injection.component

import com.changjiashuai.architecture_components_boilerplate.browse.MainActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector

/**
 * Email: changjiashuai@gmail.com
 *
 * Created by CJS on 2017/12/12 15:06.
 */
@Subcomponent
interface MainActivitySubComponent : AndroidInjector<MainActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<MainActivity>()
}