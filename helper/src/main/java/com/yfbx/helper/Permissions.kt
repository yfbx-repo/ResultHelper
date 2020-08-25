package com.yfbx.helper

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

/**
 * Author: Edward
 * Date: 2020-06-28
 * Description:Request permissions
 */
fun FragmentActivity.request(vararg permissions: String, listener: (isGrant: Boolean) -> Unit) {
    ResultFragment.get(this).getPermissions(*permissions) {
        listener.invoke(it)
    }
}

fun Fragment.request(vararg permissions: String, listener: (isGrant: Boolean) -> Unit) {
    requireActivity().request(*permissions) {
        listener.invoke(it)
    }
}
