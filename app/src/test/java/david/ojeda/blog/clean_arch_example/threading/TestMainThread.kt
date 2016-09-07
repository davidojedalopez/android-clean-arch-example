package zamp.ly.zamply.threading

import david.ojeda.blog.clean_arch_example.domain.executor.MainThread


class TestMainThread: MainThread {
    override fun post(runnable: Runnable) {
        // Tests can run on this thread, no need to invoke other threads
        runnable.run()
    }


}
