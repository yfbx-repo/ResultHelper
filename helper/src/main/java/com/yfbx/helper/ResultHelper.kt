package com.yfbx.helper

import android.annotation.TargetApi
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager

/**
 * @Author Edward
 * @Date 2019/4/3
 */

private val TAG = "ResultHelper"


fun FragmentActivity.startForResult(intent: Intent, listener: (resultCode: Int, data: Intent?) -> Unit) {
    getResultFragment(supportFragmentManager).startForResult(intent, listener)
}

fun Fragment.startForResult(intent: Intent, listener: (resultCode: Int, data: Intent?) -> Unit) {
    getResultFragment(activity!!.supportFragmentManager).startForResult(intent, listener)
}

fun FragmentActivity.request(permissions: Array<String>, listener: (isGrant: Boolean) -> Unit) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        getResultFragment(supportFragmentManager).getPermissions(permissions, listener)
    } else {
        listener.invoke(true)
    }
}

fun Fragment.request(permissions: Array<String>, listener: (isGrant: Boolean) -> Unit) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        getResultFragment(activity!!.supportFragmentManager).getPermissions(permissions, listener)
    } else {
        listener.invoke(true)
    }
}

/**
 * 获取/创建 ForResultFragment
 */
private fun getResultFragment(manager: FragmentManager): ForResultFragment {
    var fragment = manager.findFragmentByTag(TAG)
    if (fragment == null) {
        fragment = ForResultFragment()
        manager.beginTransaction().add(fragment, TAG).commitAllowingStateLoss()
        manager.executePendingTransactions()
    }
    return fragment as ForResultFragment
}

/**
 * ForResultFragment
 */
class ForResultFragment : Fragment() {

    private val resultMap = mutableMapOf<Int, (Int, Intent?) -> Unit>()
    private val permissionMap = mutableMapOf<Int, (Boolean) -> Unit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    /***
     * 跳转Activity
     */
    fun startForResult(intent: Intent, listener: (Int, Intent?) -> Unit) {
        //Can only use lower 16 bits for requestCode
        val requestCode = listener.hashCode().shr(16)
        resultMap.put(requestCode, listener)
        startActivityForResult(intent, requestCode)
    }


    /**
     * Activity 返回结果
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val listener = resultMap.remove(requestCode)
        listener?.invoke(resultCode, data)
    }


    /**
     * 请求权限
     */
    @TargetApi(Build.VERSION_CODES.M)
    fun getPermissions(permissions: Array<String>, listener: (Boolean) -> Unit) {
        //Can only use lower 16 bits for requestCode
        val requestCode = listener.hashCode().shr(16)
        permissionMap.put(requestCode, listener)
        requestPermissions(permissions, requestCode)
    }

    /**
     * 权限返回
     */
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        val listener = permissionMap.remove(requestCode)
        val isGrant = grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED
        listener?.invoke(isGrant)
    }

}