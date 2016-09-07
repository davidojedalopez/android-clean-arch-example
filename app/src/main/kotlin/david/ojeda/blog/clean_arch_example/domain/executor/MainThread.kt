package david.ojeda.blog.clean_arch_example.domain.executor

/*
This interface will define a class that will enable use cases to run certain operations on the
main UI thread. For example, if an use case needs to show an object to the UI this can be
used to make sure the show method is called on the UI thread.
 */

interface MainThread {
    /*
    Make runnable operation run in the main thread.
     */
    fun post(runnable: Runnable)

}
