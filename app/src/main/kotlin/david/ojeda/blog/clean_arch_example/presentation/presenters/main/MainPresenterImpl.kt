package david.ojeda.blog.clean_arch_example.presentation.presenters.main

import david.ojeda.blog.clean_arch_example.domain.executor.Executor
import david.ojeda.blog.clean_arch_example.domain.executor.MainThread
import david.ojeda.blog.clean_arch_example.domain.repositories.MessageRepository
import david.ojeda.blog.clean_arch_example.domain.use_cases.intro.WelcomingUseCase
import david.ojeda.blog.clean_arch_example.domain.use_cases.intro.WelcomingUseCaseImpl
import david.ojeda.blog.clean_arch_example.presentation.presenters.base.AbstractPresenter


class MainPresenterImpl:
        AbstractPresenter,
        MainPresenter,
        WelcomingUseCase.Callback {

    override fun onError(msg: String) {

    }

    override fun onMessageRetrieved(message: String) {
        mView!!.hideProgress();
        mView!!.displayWelcomeMessage(message);
    }

    override fun onRetrievalFailed(error: String) {
        mView!!.hideProgress();
        onError(error);
    }

    var mView: MainPresenter.View? = null
    var mMessageRepository: MessageRepository? = null

    constructor(executor: Executor,
                mainThread: MainThread,
                view: MainPresenter.View,
                messageRepository: MessageRepository) : super(executor, mainThread) {
        mView = view
        mMessageRepository = messageRepository
    }

    override fun create() {

    }

    override fun resume() {
        mView!!.showProgress()

        // Initialize the use case
        val useCase = WelcomingUseCaseImpl(
                mExecutor!!,
                mMainThread!!,
                this,
                mMessageRepository!!)

        // Run the use case
        useCase.execute()
    }

    override fun pause() {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun stop() {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun destroy() {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}