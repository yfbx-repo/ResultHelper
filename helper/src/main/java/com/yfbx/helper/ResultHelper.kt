@file:Suppress("unused")

package com.yfbx.helper

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment

/**
 * Author: Edward
 * Date: 2020-06-02
 * Description:
 */

/**
 * Start Activity for result with args.
 */
inline fun <reified T> ComponentActivity.launchFor(vararg param: Pair<String, Any?>, noinline callback: (ActivityResult) -> Unit) {
    launchFor(Intent(this, T::class.java).fillArgs(param), callback)
}

/**
 * Start Activity for result simply with target Activity(without args.)
 */
inline fun <reified T> ComponentActivity.launchFor(noinline callback: (ActivityResult) -> Unit) {
    launchFor(Intent(this, T::class.java), callback)
}

/**
 *  Start Activity for result with Intent
 */
fun ComponentActivity.launchFor(intent: Intent, callback: (ActivityResult) -> Unit) {
    registerForActivityResult(ActivityResultContracts.StartActivityForResult(), ActivityResultCallback(callback)).launch(intent)
}

/**
 * Fragment
 */
inline fun <reified T> Fragment.launchFor(vararg param: Pair<String, Any?>, noinline callback: (ActivityResult) -> Unit) {
    launchFor(Intent(context, T::class.java).fillArgs(param), callback)
}

inline fun <reified T> Fragment.launchFor(noinline callback: (ActivityResult) -> Unit) {
    launchFor(Intent(context, T::class.java), callback)
}

fun Fragment.launchFor(intent: Intent, callback: (ActivityResult) -> Unit) {
    registerForActivityResult(ActivityResultContracts.StartActivityForResult(), ActivityResultCallback(callback)).launch(intent)
}
