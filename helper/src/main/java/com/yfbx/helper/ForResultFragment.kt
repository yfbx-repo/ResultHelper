package com.yfbx.helper

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment

/**
 * @Author Edward
 * @Date 2019/5/29 0029
 * @Description:无界面Fragment,处理返回结果
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
        val requestCode = resultMap.size + 1
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
    private fun isGranted(permissions: Array<String>): Boolean {
        val checkResult = permissions.map { ActivityCompat.checkSelfPermission(context!!, it) }
        return !checkResult.contains(PackageManager.PERMISSION_DENIED)
    }

    /**
     * 请求权限
     */
    fun getPermissions(permissions: Array<String>, listener: (Boolean) -> Unit) {
        //已有权限
        if (isGranted(permissions)) {
            listener.invoke(true)
            return
        }

        //申请权限
        val requestCode = permissionMap.size + 1
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