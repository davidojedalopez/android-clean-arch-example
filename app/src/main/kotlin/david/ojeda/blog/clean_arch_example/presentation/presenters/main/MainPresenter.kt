package david.ojeda.blog.clean_arch_example.presentation.presenters.main

import david.ojeda.blog.clean_arch_example.presentation.presenters.base.BasePresenter
import david.ojeda.blog.clean_arch_example.presentation.views.BaseView


interface MainPresenter: BasePresenter {

    interface View: BaseView {
        fun displayWelcomeMessage(msg: String)
    }
}