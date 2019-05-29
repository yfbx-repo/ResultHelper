package com.yfbx.helper

import android.app.Activity
import android.app.Application
import android.os.Bundle
import java.lang.ref.WeakReference

/**
 * @Author Edward
 * @Date 2019/5/29 0029
 * @Description:生命周期回调,获取栈顶Activity
 */
class ResultLifeCycle : Application.ActivityLifecycleCallbacks {


    var stackTop: WeakReference<Activity>? = null


    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
        stackTop = WeakReference(activity!!)
    }

    override fun onActivityStarted(activity: Activity?) {
    }


    override fun onActivityResumed(activity: Activity?) {
    }


    override fun onActivityPaused(activity: Activity?) {
    }


    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
    }


    override fun onActivityStopped(activity: Activity?) {
    }


    override fun onActivityDestroyed(activity: Activity?) {
    }

}