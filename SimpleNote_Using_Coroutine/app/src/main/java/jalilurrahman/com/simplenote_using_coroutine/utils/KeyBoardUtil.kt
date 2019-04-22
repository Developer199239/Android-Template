package jalilurrahman.com.simplenote_using_coroutine.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import jalilurrahman.com.simplenote_using_coroutine.App

object KeyBoardUtil {
    fun showSoftInput(activity: Activity, editText: EditText) {
        editText.requestFocus()
        activity.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
    }

    fun hideSoftInput(activity: Activity) {
        var view = activity.currentFocus
        if(view == null) {
            view = View(activity)
        }
        val inputMethodManager = App.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken,0)
    }
}