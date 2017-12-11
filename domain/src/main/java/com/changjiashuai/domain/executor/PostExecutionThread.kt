package com.changjiashuai.domain.executor

import io.reactivex.Scheduler

/**
 * Email: changjiashuai@gmail.com
 *
 * Created by CJS on 2017/12/11 10:44.
 *
 * 创建线程的抽象，将执行上下文从任何线程更改为任何其他线程。
 * 例如将UI线程封装到内部是有用的，由于某些作业将在后台完成，所以要封装一个UI线程
 * 该接口的实现将改变上下文并更新UI。
 */
interface PostExecutionThread {
    val scheduler: Scheduler
}