package david.ojeda.blog.clean_arch_example.domain.use_cases.intro

import david.ojeda.blog.clean_arch_example.domain.executor.Executor
import david.ojeda.blog.clean_arch_example.domain.executor.MainThread
import david.ojeda.blog.clean_arch_example.domain.repositories.MessageRepository
import david.ojeda.blog.clean_arch_example.domain.use_cases.base.AbstractUseCase


class WelcomingUseCaseImpl:
        AbstractUseCase,
        WelcomingUseCase {

    var mMessageRepository: MessageRepository? = null
    var mCallback: WelcomingUseCase.Callback? = null

    constructor(threadExecutor: Executor,
                mainThread: MainThread,
                callback: WelcomingUseCase.Callback,
                messageRepository: MessageRepository
    ) : super(threadExecutor, mainThread){
        mMessageRepository = messageRepository
        mCallback = callback
    }

    override fun run() {

        // Retrieve message
        val message: String? = mMessageRepository!!.getWelcomeMessage()

        // Check if message retrieval succeed
        if (!message.isNullOrEmpty())
            // Message retrieved, notify UI on the main thread
            postMessage(message!!)
        else
            // Message retrieval failed, notify on the main thread
            notifyError()

    }

    private fun notifyError() {
        mMainThread!!.post(Runnable {
            mCallback!!.onRetrievalFailed("Nothing to welcome you with :(")
        })
    }

    private fun postMessage(msg: String) {
        mMainThread!!.post(Runnable {
            mCallback!!.onMessageRetrieved(msg)
        })
    }
}