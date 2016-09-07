package david.ojeda.blog.clean_arch_example.presentation.presenters.base


interface BasePresenter {
    fun create()

    fun resume()

    fun pause()

    fun stop()

    fun destroy()

    fun onError(msg: String)

}
