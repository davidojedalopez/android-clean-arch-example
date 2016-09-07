package david.ojeda.blog.clean_arch_example.domain.executor

import david.ojeda.blog.clean_arch_example.domain.use_cases.base.AbstractUseCase


interface Executor {
    /*
    This method should call the use case's run method and thus start the use case.
    This should be called on a background thread as use cases might do lengthy operations.
     */
    fun execute(useCase: AbstractUseCase)

}
