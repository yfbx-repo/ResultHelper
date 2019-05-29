package com.yfbx.resulthelper

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.yfbx.helper.ResultHelper

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //请求权限
        ResultHelper.request(Manifest.permission.WRITE_EXTERNAL_STORAGE) {


        }

        //Activity for Result
        ResultHelper.startActivity(Intent()) { code, data ->

        }
    }


}
