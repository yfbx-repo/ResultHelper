package com.yfbx.helper

import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

/**
 * @Author Edward
 * @Date 2019/4/3
 */


/**
 * Start Activity For Result
 */
fun FragmentActivity.startForResult(intent: Intent, listener: (resultCode: Int, data: Intent?) -> Unit) {
    getFragment().startForResult(intent, listener)
}


fun Fragment.startForResult(intent: Intent, listener: (resultCode: Int, data: Intent?) -> Unit) {
    activity?.getFragment()?.startForResult(intent, listener)
}


/**
 * 请求权限
 */
fun FragmentActivity.request(vararg permissions: String, listener: (isGrant: Boolean) -> Unit) {
    getFragment().getPermissions(permissions.asList().toTypedArray(), listener)
}

fun Fragment.request(vararg permissions: String, listener: (isGrant: Boolean) -> Unit) {
    activity?.getFragment()?.getPermissions(permissions.asList().toTypedArray(), listener)
}


/**
 * 获取/创建 ForResultFragment
 */
private fun FragmentActivity.getFragment(): ForResultFragment {
    val tag = "ForResultFragment"
    var fragment = supportFragmentManager.findFragmentByTag(tag)
    if (fragment == null) {
        fragment = ForResultFragment()
        supportFragmentManager.beginTransaction().add(fragment, tag).commitAllowingStateLoss()
        supportFragmentManager.executePendingTransactions()
    }
    return fragment as ForResultFragment
}