@file:Suppress("unused")

package com.yfbx.helper

import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.yfbx.helper.helper.ResultFragment
import com.yfbx.helper.helper.fillArgs

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
 * Start Activity for result simply with target Activity(without args.)
 */
inline fun <reified T> FragmentActivity.startForResult(noinline callback: (resultCode: Int, data: Intent?) -> Unit) {
    startForResult(Intent(this, T::class.java), callback)
}

/**
 * Start Activity for result with Intent
 */
fun FragmentActivity.startForResult(intent: Intent, callback: (resultCode: Int, data: Intent?) -> Unit) {
    ResultFragment.get(this).startForResult(intent, callback)
}


/**
 * Request permissions
 */
fun FragmentActivity.request(vararg permissions: String, listener: (isGrant: Boolean) -> Unit) {
    ResultFragment.get(this).getPermissions(*permissions) {
        listener.invoke(it)
    }
}


inline fun <reified T> Fragment.startForResult(vararg param: Pair<String, Any?>, noinline callback: (resultCode: Int, data: Intent?) -> Unit) {
    startForResult(Intent(context, T::class.java).fillArgs(param), callback)
}

inline fun <reified T> Fragment.startForResult(noinline callback: (resultCode: Int, data: Intent?) -> Unit) {
    startForResult(Intent(context, T::class.java), callback)
}

fun Fragment.startForResult(intent: Intent, listener: (resultCode: Int, data: Intent?) -> Unit) {
    requireActivity().startForResult(intent, listener)
}

fun Fragment.request(vararg permissions: String, listener: (isGrant: Boolean) -> Unit) {
    ResultFragment.get(requireActivity()).getPermissions(*permissions) {
        listener.invoke(it)
    }
}

