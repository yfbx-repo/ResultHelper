package com.yfbx.helper

import android.app.Application
import android.content.Intent
import android.os.Build
import android.support.v4.app.FragmentActivity

/**
 * @Author Edward
 * @Date 2019/4/3
 */
object ResultHelper {


    private val TAG = "ResultHelper"

    private lateinit var lifeCycle: ResultLifeCycle

    /**
     * 初始化
     */
    internal fun init(context: Application) {
        lifeCycle = ResultLifeCycle()
        context.registerActivityLifecycleCallbacks(lifeCycle)
    }


    /**
     * Start Activity For Result
     */
    fun startActivity(intent: Intent, listener: (resultCode: Int, data: Intent?) -> Unit) {
        getFragment().startForResult(intent, listener)
    }


    /**
     * 请求权限
     */
    fun request(vararg permissions: String, listener: (isGrant: Boolean) -> Unit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getFragment().getPermissions(permissions.asList().toTypedArray(), listener)
        } else {
            listener.invoke(true)
        }
    }


    /**
     * 获取/创建 ForResultFragment
     */
    private fun getFragment(): ForResultFragment {
        val activity = lifeCycle.stackTop!!.get()
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

}