package david.ojeda.blog.clean_arch_example.presentation.presenters.base

import david.ojeda.blog.clean_arch_example.domain.executor.Executor
import david.ojeda.blog.clean_arch_example.domain.executor.MainThread

/*
This is a base class for all presenters which are communicating with use cases. This base class
 will hold a reference to the Executor and MainThread objects that are needed for running
 use cases in a background thread.
 */
abstract class AbstractPresenter {

    var mExecutor: Executor? = null
    var mMainThread: MainThread? = null

    constructor(executor: Executor, mainThread: MainThread){
        mExecutor = executor
        mMainThread = mainThread
    }
}
