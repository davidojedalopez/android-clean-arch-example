package david.ojeda.blog.clean_arch_example

import android.test.suitebuilder.annotation.SmallTest
import david.ojeda.blog.clean_arch_example.domain.executor.Executor
import david.ojeda.blog.clean_arch_example.domain.executor.MainThread
import david.ojeda.blog.clean_arch_example.domain.repositories.MessageRepository
import david.ojeda.blog.clean_arch_example.domain.use_cases.intro.WelcomingUseCase
import david.ojeda.blog.clean_arch_example.domain.use_cases.intro.WelcomingUseCaseImpl
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import zamp.ly.zamply.threading.TestMainThread

@SmallTest
class WelcomingUseCaseTest {

    var mMainThread: MainThread? = null
    @Mock
    var mMockedExecutor: Executor? = null
    @Mock
    var mMockedMessageRepository: MessageRepository? = null
    @Mock
    var mMockedCallback: WelcomingUseCase.Callback? = null

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        mMainThread = TestMainThread()
    }

    @Test
    fun welcomingMessageShouldAppearOnUi() {
        val msg = "Welcome buddy!"

        `when`(mMockedMessageRepository!!.getWelcomeMessage()).thenReturn(msg)

        val useCase = WelcomingUseCaseImpl(
                mMockedExecutor!!,
                mMainThread!!,
                mMockedCallback!!,
                mMockedMessageRepository!!
        )

        useCase.run()

        Mockito.verify(mMockedMessageRepository)!!.getWelcomeMessage()
        Mockito.verifyNoMoreInteractions(mMockedMessageRepository)
        Mockito.verify(mMockedCallback)!!.onMessageRetrieved(msg)

    }

    @Test
    fun welcomingMessageShouldShowAnError() {
        `when`(mMockedMessageRepository!!.getWelcomeMessage()).thenReturn(null)

        val useCase = WelcomingUseCaseImpl(
                mMockedExecutor!!,
                mMainThread!!,
                mMockedCallback!!,
                mMockedMessageRepository!!
        )

        useCase.run()

        Mockito.verify(mMockedMessageRepository)!!.getWelcomeMessage()
        Mockito.verifyNoMoreInteractions(mMockedMessageRepository)

        val errorMsg = "Nothing to welcome you with :("
        Mockito.verify(mMockedCallback)!!.onRetrievalFailed(errorMsg)

    }
}