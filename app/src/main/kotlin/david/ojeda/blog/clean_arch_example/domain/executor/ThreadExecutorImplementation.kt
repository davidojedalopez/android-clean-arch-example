package david.ojeda.blog.clean_arch_example.domain.executor

import david.ojeda.blog.clean_arch_example.domain.use_cases.base.AbstractUseCase
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit


/*
This singleton class will make sure that each use case operation gets a background thread.
 */

class ThreadExecutorImplementation: Executor {

    var sThreadExecutor: ThreadExecutorImplementation? = null
    val MAX_POOL_SIZE = 5
    val CORE_POOL_SIZE = 3
    val KEEP_ALIVE_TIME = 120
    val TIME_UNIT = TimeUnit.SECONDS
    val WORK_QUEUE = LinkedBlockingQueue<Runnable>()

    var mThreadPoolExecutor: ThreadPoolExecutor? = null

    constructor(){
        val keepAlive: Long = KEEP_ALIVE_TIME.toLong()
        mThreadPoolExecutor = ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                keepAlive,
                TIME_UNIT,
                WORK_QUEUE)
    }

    override fun execute(useCase: AbstractUseCase) {
        mThreadPoolExecutor?.submit {
            // Run the main logic
            useCase.run()

            // Mask it as finished
            useCase.onFinished()
        }
    }

    /*
    Returns a singleton instance of this executor. If the executor is not initialized then it
    initializes it and returns the instance.
     */
    fun getInstance(): Executor {
        if(sThreadExecutor == null){
            sThreadExecutor = ThreadExecutorImplementation()
        }

        return sThreadExecutor as ThreadExecutorImplementation
    }


}
