package com.yfbx.helper

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity

/**
 * @Author Edward
 * @Date 2019/5/29 0029
 * @Description:
 */

private val TAG = "ResultHelper"


/**
 * Start Activity For Result
 */
fun FragmentActivity.startForResult(intent: Intent, listener: (resultCode: Int, data: Intent?) -> Unit) {
    getFragment(this).startForResult(intent, listener)
}


fun Fragment.startForResult(intent: Intent, listener: (resultCode: Int, data: Intent?) -> Unit) {
    getFragment(activity!!).startForResult(intent, listener)
}


/**
 * 请求权限
 */
fun FragmentActivity.request(vararg permissions: String, listener: (isGrant: Boolean) -> Unit) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        getFragment(this).getPermissions(permissions.asList().toTypedArray(), listener)
    } else {
        listener.invoke(true)
    }
}

fun Fragment.request(vararg permissions: String, listener: (isGrant: Boolean) -> Unit) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        getFragment(activity!!).getPermissions(permissions.asList().toTypedArray(), listener)
    } else {
        listener.invoke(true)
    }
}


/**
 * 获取/创建 ForResultFragment
 */
private fun getFragment(activity: Activity): ForResultFragment {
    if (activity is FragmentActivity) {
        val manager = activity.supportFragmentManager
        var fragment = manager.findFragmentByTag(TAG)
        if (fragment == null) {
            fragment = ForResultFragment()
            manager.beginTransaction().add(fragment, TAG).commitAllowingStateLoss()
            manager.executePendingTransactions()
        }
        return fragment as ForResultFragment
    } else {
        throw Exception("需要 FragmentActivity")
    }
}