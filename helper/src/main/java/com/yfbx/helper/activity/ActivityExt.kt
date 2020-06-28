@file:Suppress("unused")

package com.yfbx.helper.activity

import android.app.Activity
import android.content.Intent
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts.*
import com.yfbx.helper.ResultRegister
import com.yfbx.helper.fillArgs
import java.util.concurrent.atomic.AtomicInteger

/**
 * Author: Edward
 * Date: 2020-06-02
 * Description:
 */

/**
 * Auto Request Code
 */
private val nextLocalRequestCode = AtomicInteger()


/**
 * Start Activity for result with args.
 */
inline fun <reified T> Activity.startFor(vararg param: Pair<String, Any?>, noinline callback: (ActivityResult) -> Unit) {
    startFor(Intent(this, T::class.java).fillArgs(param), callback)
}

/**
 * Start Activity for result simply with target Activity(without args.)
 */
inline fun <reified T> Activity.startFor(noinline callback: (ActivityResult) -> Unit) {
    startFor(Intent(this, T::class.java), callback)
}

/**
 *  Start Activity for result with Intent
 */
fun Activity.startFor(intent: Intent, callback: (ActivityResult) -> Unit) {
    registerForActivityResult(StartActivityForResult(), callback).launch(intent)
}


/**
 * Request permission
 */
fun Activity.getPermission(permission: String, callback: (isGrant: Boolean) -> Unit) {
    registerForActivityResult(RequestPermission(), callback).launch(permission)
}

/**
 * Request permissions
 */
fun Activity.getPermission(vararg permission: String, callback: (Map<String, Boolean>) -> Unit) {
    registerForActivityResult(RequestMultiplePermissions(), callback).launch(permission)
}

/**
 * This method registers every single request for result.
 * It's state will not be saved with [Activity.onSaveInstanceState].
 */
fun <I, O> Activity.registerForActivityResult(contract: ActivityResultContract<I, O>, callback: (O) -> Unit): ActivityResultLauncher<I> {
    val key = "activity_rq#" + nextLocalRequestCode.getAndIncrement()
    return ResultRegister(this).register(key, contract, ActivityResultCallback(callback))
}

