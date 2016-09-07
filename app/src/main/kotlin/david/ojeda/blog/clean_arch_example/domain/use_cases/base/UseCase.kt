package david.ojeda.blog.clean_arch_example.domain.use_cases.base


interface UseCase {
    /*
    This is the main method that starts an use case. It will make sure that the use case operation
    is done on a background thread.
     */
    fun execute()

}