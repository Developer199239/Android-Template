package jalilurrahman.com.simplenote_using_coroutine.ui.activity

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import jalilurrahman.com.simplenote_using_coroutine.viewmodels.BaseViewModel

abstract class BaseActivity<VM: BaseViewModel> : AppCompatActivity() {
    protected abstract fun viewModel(): VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun setToolbar(){
        providerToolBar()?.let {
            setSupportActionBar(it)
        }
    }

    abstract fun layoutId(): Int

    open fun prepareBeforeInitView(){}
    open fun initView(){}
    open fun initListeners(){}

    private fun observeVM(){
        lifecycle.addObserver(viewModel())
    }

    open fun providerToolBar(): Toolbar? = null

    override fun onDestroy() {
        viewModel().let {
            lifecycle.removeObserver(it)
        }
        super.onDestroy()
    }
}