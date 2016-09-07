package david.ojeda.blog.clean_arch_example.domain.use_cases.base

import david.ojeda.blog.clean_arch_example.domain.executor.Executor
import david.ojeda.blog.clean_arch_example.domain.executor.MainThread


abstract class AbstractUseCase: UseCase {

    var mThreadExecutor: Executor? = null
    var mMainThread: MainThread? = null

    var mIsCanceled: Boolean? = null
    var mIsRunning: Boolean? = null

    constructor(threadExecutor: Executor, mainThread: MainThread){
        mThreadExecutor = threadExecutor
        mMainThread = mainThread
    }

    /*
    This method contains the actual business logic of the use case. It SHOULD NOT BE USED DIRECTLY
    but, instead, a developer should call the execute() method of an use case to make sure the
    operation is done on a background thread.

    This method should only be called directly while doing unit/integration tests. That is the only
    reason it is declared public as to help with easier testing.
     */
    abstract fun run()

    fun cancel(){
        mIsCanceled = true
        mIsRunning = false
    }

    fun isRunning():Boolean? {
        return mIsRunning
    }

    fun onFinished(){
        mIsCanceled = false
        mIsRunning = false
    }

    override fun execute(){
        this.mIsRunning = true
        mThreadExecutor?.execute(this)
    }
}