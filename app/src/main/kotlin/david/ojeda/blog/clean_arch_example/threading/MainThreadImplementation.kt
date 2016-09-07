package david.ojeda.blog.clean_arch_example.threading

import android.os.Handler
import android.os.Looper
import david.ojeda.blog.clean_arch_example.domain.executor.MainThread


/*
This class makes sure that the runnable we provide will be run on the main UI thread.
 */
class MainThreadImplementation: MainThread {

    var sMainThread: MainThread? = null
    var mHandler: Handler? = null

    constructor(){
        mHandler = Handler(Looper.getMainLooper())
    }

    override fun post(runnable: Runnable) {
        mHandler?.post(runnable)
    }

    fun getInstance(): MainThread {
        if(sMainThread == null){
            sMainThread = MainThreadImplementation()
        }

        return sMainThread as MainThread
    }

}
