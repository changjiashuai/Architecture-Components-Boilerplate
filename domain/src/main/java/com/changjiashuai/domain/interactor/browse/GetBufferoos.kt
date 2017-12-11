package com.changjiashuai.domain.interactor.browse

import com.changjiashuai.domain.executor.PostExecutionThread
import com.changjiashuai.domain.executor.ThreadExecutor
import com.changjiashuai.domain.executor.model.Bufferoo
import com.changjiashuai.domain.interactor.FlowableUseCase
import com.changjiashuai.domain.repository.BufferooRepository
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Email: changjiashuai@gmail.com
 *
 * Created by CJS on 2017/12/11 11:18.
 *
 * 使用用于从[BufferooRepository]中检索的[Bufferoo]实例的[List]
 */
open class GetBufferoos @Inject constructor(
        val bufferooRepository: BufferooRepository,
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread
) : FlowableUseCase<List<Bufferoo>, Void?>(threadExecutor, postExecutionThread) {

    public override fun buildUseCaseObservable(params: Void?): Flowable<List<Bufferoo>> {
        return bufferooRepository.getBufferoos()
    }
}