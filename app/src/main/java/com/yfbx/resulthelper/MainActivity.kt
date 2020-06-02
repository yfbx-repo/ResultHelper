package com.yfbx.resulthelper

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yfbx.helper.getPermission
import com.yfbx.helper.launchFor
import com.yfbx.helper.request
import com.yfbx.helper.startForResult

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //申请权限
        request(Manifest.permission.WRITE_EXTERNAL_STORAGE) {
            if (it) {
                //获得权限
            }
        }

        //Activity for Result
        startForResult(Intent()) { code, data ->
            if (code == Activity.RESULT_OK && data != null) {
                //返回结果处理
            }
        }


        launchFor<MainActivity>("k1" to "v1", "k2" to "v2") {

        }

        getPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) {

        }


    }


}
