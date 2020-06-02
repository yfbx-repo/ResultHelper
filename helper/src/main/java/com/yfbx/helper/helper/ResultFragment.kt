package com.yfbx.helper.helper

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import java.util.concurrent.atomic.AtomicInteger

/**
 * Author Edward
 * Date 2019/5/29
 * Description:无界面Fragment,处理返回结果
 */
class ResultFragment private constructor() : Fragment() {

    companion object {
        private const val TAG = "ResultFragment"
        fun get(activity: FragmentActivity): ResultFragment {
            val manager = activity.supportFragmentManager
            var fragment = manager.findFragmentByTag(TAG)
            if (fragment == null) {
                fragment = ResultFragment()
                manager.beginTransaction().add(fragment, TAG).commitAllowingStateLoss()
                manager.executePendingTransactions()
            }
            return fragment as ResultFragment
        }
    }

    private val nextLocalRequestCode = AtomicInteger()
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
        val requestCode = nextLocalRequestCode.getAndIncrement()
        resultMap[requestCode] = listener
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
     * 是否有权限
     */
    private fun isGranted(vararg permissions: String): Boolean {
        val checkResult = permissions.map { ActivityCompat.checkSelfPermission(requireContext(), it) }
        return !checkResult.contains(PackageManager.PERMISSION_DENIED)
    }

    /**
     * 请求权限
     */
    fun getPermissions(vararg permissions: String, listener: (Boolean) -> Unit) {
        //已有权限
        if (isGranted(*permissions)) {
            listener.invoke(true)
            return
        }

        //申请权限
        val requestCode = nextLocalRequestCode.getAndIncrement()
        permissionMap[requestCode] = listener
        requestPermissions(permissions, requestCode)
    }

    /**
     * 权限返回
     */
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        val listener = permissionMap.remove(requestCode)
        val isGrant = !grantResults.contains(PackageManager.PERMISSION_DENIED)
        listener?.invoke(isGrant)
    }
}