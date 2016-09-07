package david.ojeda.blog.clean_arch_example.domain.repositories


interface MessageRepository {

    fun getWelcomeMessage(): String

}