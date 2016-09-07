package david.ojeda.blog.clean_arch_example.domain.use_cases.intro

import david.ojeda.blog.clean_arch_example.domain.use_cases.base.UseCase


interface WelcomingUseCase: UseCase {

    interface Callback {

        fun onMessageRetrieved(message: String)

        fun onRetrievalFailed(error: String)

    }
}