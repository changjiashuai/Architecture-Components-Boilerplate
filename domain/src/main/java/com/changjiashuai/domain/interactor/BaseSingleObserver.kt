package com.changjiashuai.domain.interactor

import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable

/**
 * Email: changjiashuai@gmail.com
 *
 * Created by CJS on 2017/12/11 10:58.
 */
open class BaseSingleObserver<T> : SingleObserver<T> {

    override fun onSubscribe(d: Disposable) {}

    override fun onError(throwable: Throwable) {}

    override fun onSuccess(t: T) {}
}