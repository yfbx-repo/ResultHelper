package com.yfbx.helper

import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

/**
 * @Author Edward
 * @Date 2019/4/3
 */

/**
 * Start Activity for result with args.
 */
inline fun <reified T> FragmentActivity.startForResult(vararg param: Pair<String, Any?>, noinline callback: (resultCode: Int, data: Intent?) -> Unit) {
    startForResult(Intent(this, T::class.java).fillArgs(param), callback)
}

/**
 * Start Activity for result with Intent
 */
fun FragmentActivity.startForResult(intent: Intent, callback: (resultCode: Int, data: Intent?) -> Unit) {
    ResultFragment.get(this).startForResult(intent, callback)
}


inline fun <reified T> Fragment.startForResult(vararg param: Pair<String, Any?>, noinline callback: (resultCode: Int, data: Intent?) -> Unit) {
    requireActivity().startForResult<T>(*param) { code, data ->
        callback.invoke(code, data)
    }
}


fun Fragment.startForResult(intent: Intent, callback: (resultCode: Int, data: Intent?) -> Unit) {
    requireActivity().startForResult(intent, callback)
}
