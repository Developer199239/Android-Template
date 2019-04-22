package jalilurrahman.com.simplenote_using_coroutine.viewmodels

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

open class BaseViewModel : ViewModel(), LifecycleObserver, CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private val mLaunchManager: MutableList<Job> = mutableListOf()

    protected fun launchOnUITryCatch(
        tryBlock: suspend CoroutineScope.() -> Unit,
        cacheBlock: suspend CoroutineScope.(Throwable) -> Unit,
        finallyBlock: suspend CoroutineScope.() -> Unit,
        handleCancellationExceptionManually: Boolean
    ) {
        launchOnUI {
            tryCatch(tryBlock,cacheBlock,finallyBlock,handleCancellationExceptionManually)
        }
    }

    /**
     * add launch task to [mLaunchManager]
     * */
    private fun launchOnUI(block: suspend CoroutineScope.() -> Unit) {
        val job = launch { block() }
        mLaunchManager.add(job)
        job.invokeOnCompletion {
            mLaunchManager.remove(job)
        }
    }

    private suspend fun tryCatch(
        tryBlock: suspend CoroutineScope.() -> Unit,
        cacheBlock: suspend CoroutineScope.(Throwable) -> Unit,
        finallyBlock: suspend CoroutineScope.() -> Unit,
        handleCancellationExceptionManually: Boolean = false
    ) {
        coroutineScope {
            try {
                tryBlock()
            }catch (e: Throwable) {
                if(e !is CancellationException || handleCancellationExceptionManually) {
                    cacheBlock(e)
                } else {
                    throw e
                }
            } finally {
                finallyBlock()
            }
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestory(){
        clearLaunchTask()
    }

    private fun clearLaunchTask(){
        mLaunchManager.clear()
    }


}