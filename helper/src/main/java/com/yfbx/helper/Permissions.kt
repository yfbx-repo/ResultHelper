package com.yfbx.helper

import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment

/**
 * Author: Edward
 * Date: 2020-06-28
 * Description:Request permissions
 */

fun ComponentActivity.permitFor(permission: String, callback: (isGrant: Boolean) -> Unit) {
    registerForActivityResult(ActivityResultContracts.RequestPermission(), callback).launch(permission)
}

fun ComponentActivity.permitsFor(vararg permission: String, callback: (Map<String, Boolean>) -> Unit) {
    registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions(), callback).launch(permission)
}

fun Fragment.permitFor(permission: String, callback: (isGrant: Boolean) -> Unit) {
    registerForActivityResult(ActivityResultContracts.RequestPermission(), callback).launch(permission)
}

fun Fragment.permitsFor(vararg permission: String, callback: (Map<String, Boolean>) -> Unit) {
    registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions(), callback).launch(permission)
}

