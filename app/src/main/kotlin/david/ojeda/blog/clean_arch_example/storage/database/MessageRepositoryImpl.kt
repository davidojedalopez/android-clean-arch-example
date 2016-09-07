package david.ojeda.blog.clean_arch_example.storage.database

import david.ojeda.blog.clean_arch_example.domain.repositories.MessageRepository


class MessageRepositoryImpl: MessageRepository {
    override fun getWelcomeMessage(): String {
        val msg = "Welcome, friend!"

        // Let's simulate some network/database lag
        try {
            Thread.sleep(2000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        return msg
    }

}