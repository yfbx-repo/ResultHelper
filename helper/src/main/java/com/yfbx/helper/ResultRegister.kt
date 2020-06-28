package com.yfbx.helper

import android.app.Activity
import android.content.Intent
import android.content.IntentSender
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Process
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import java.util.*

/**
 * Author: Edward
 * Date: 2020-06-02
 * Description:[ComponentActivity.mActivityResultRegistry]
 */
class ResultRegister(private val activity: Activity) : ActivityResultRegistry() {

    override fun <I : Any?, O : Any?> onLaunch(requestCode: Int, contract: ActivityResultContract<I, O>, input: I, options: ActivityOptionsCompat?) {
        // Immediate result path
        val synchronousResult = contract.getSynchronousResult(activity, input)
        if (synchronousResult != null) {
            Handler(Looper.getMainLooper()).post { dispatchResult(requestCode, synchronousResult.value) }
            return
        }

        val intent = contract.createIntent(activity, input)
        when (intent.action) {
            ActivityResultContracts.RequestMultiplePermissions.ACTION_REQUEST_PERMISSIONS -> requestPermissions(requestCode, intent)
            ActivityResultContracts.StartIntentSenderForResult.ACTION_INTENT_SENDER_REQUEST -> startIntentSenderForResult(requestCode, intent, options)
            else -> sendActivityForResult(requestCode, intent, options)
        }
    }

    /**
     * requestPermissions path
     */
    private fun requestPermissions(requestCode: Int, intent: Intent) {
        val permissions = intent.getStringArrayExtra(ActivityResultContracts.RequestMultiplePermissions.EXTRA_PERMISSIONS)
                ?: return
        val nonGrantedPermissions: MutableList<String> = ArrayList()
        for (permission in permissions) {
            if (activity.checkPermission(permission, Process.myPid(), Process.myUid()) != PackageManager.PERMISSION_GRANTED) {
                nonGrantedPermissions.add(permission)
            }
        }
        if (nonGrantedPermissions.isNotEmpty()) {
            ActivityCompat.requestPermissions(activity, nonGrantedPermissions.toTypedArray(), requestCode)
        }
    }

    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    private fun startIntentSenderForResult(requestCode: Int, intent: Intent, options: ActivityOptionsCompat?) {
        val request: IntentSenderRequest = intent.getParcelableExtra(ActivityResultContracts.StartIntentSenderForResult.EXTRA_INTENT_SENDER_REQUEST)
        try {
            ActivityCompat.startIntentSenderForResult(
                    activity, request.intentSender,
                    requestCode, request.fillInIntent,
                    request.flagsMask, request.flagsValues, 0,
                    options?.toBundle()
            )
        } catch (e: IntentSender.SendIntentException) {
            Handler(Looper.getMainLooper()).post {
                dispatchResult(
                        requestCode, Activity.RESULT_CANCELED,
                        Intent().setAction(ActivityResultContracts.StartIntentSenderForResult.ACTION_INTENT_SENDER_REQUEST)
                                .putExtra(ActivityResultContracts.StartIntentSenderForResult.EXTRA_SEND_INTENT_EXCEPTION, e)
                )
            }
        }
    }

    private fun sendActivityForResult(requestCode: Int, intent: Intent, options: ActivityOptionsCompat?) {
        var optionsBundle: Bundle? = null
        if (intent.hasExtra(ActivityResultContracts.StartActivityForResult.EXTRA_ACTIVITY_OPTIONS_BUNDLE)) {
            optionsBundle =
                    intent.getBundleExtra(ActivityResultContracts.StartActivityForResult.EXTRA_ACTIVITY_OPTIONS_BUNDLE)
        } else if (options != null) {
            optionsBundle = options.toBundle()
        }
        ActivityCompat.startActivityForResult(activity, intent, requestCode, optionsBundle)
    }
}