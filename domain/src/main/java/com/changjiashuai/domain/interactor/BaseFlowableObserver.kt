package com.changjiashuai.domain.interactor

import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable

/**
 * Email: changjiashuai@gmail.com
 *
 * Created by CJS on 2017/12/11 11:00.
 */
open class BaseFlowableObserver<T> : SingleObserver<T> {

    override fun onError(throwable: Throwable) {}

    override fun onSuccess(t: T) {}

    override fun onSubscribe(d: Disposable) {}
}