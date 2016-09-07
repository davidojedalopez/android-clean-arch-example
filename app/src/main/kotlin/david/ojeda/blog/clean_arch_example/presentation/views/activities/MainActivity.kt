package david.ojeda.blog.clean_arch_example.presentation.views.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import david.ojeda.blog.clean_arch_example.R
import david.ojeda.blog.clean_arch_example.domain.executor.ThreadExecutorImplementation
import david.ojeda.blog.clean_arch_example.presentation.presenters.main.MainPresenter
import david.ojeda.blog.clean_arch_example.presentation.presenters.main.MainPresenterImpl
import david.ojeda.blog.clean_arch_example.storage.database.MessageRepositoryImpl
import david.ojeda.blog.clean_arch_example.threading.MainThreadImplementation
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity :
        AppCompatActivity(),
        MainPresenter.View {

    var mPresenter: MainPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Create presenter
        mPresenter = MainPresenterImpl (
                ThreadExecutorImplementation().getInstance(),
                MainThreadImplementation().getInstance(),
                this,
                MessageRepositoryImpl()
        )

        mPresenter!!.create()
    }

    override fun onResume(){
        super.onResume()

        mPresenter!!.resume()
    }


    override fun displayWelcomeMessage(msg: String) {
        myText.text = msg
    }

    override fun showProgress() {

    }

    override fun hideProgress() {

    }
}
