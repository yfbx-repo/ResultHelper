package com.yfbx.resulthelper

import android.Manifest
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yfbx.helper.launchFor
import com.yfbx.helper.request

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //startActivityForResult
        launchFor<MainActivity>("k1" to "v1", "k2" to "v2") {

        }

        //requestPermission
        request(Manifest.permission.WRITE_EXTERNAL_STORAGE) { isGrant: Boolean ->
            if (isGrant) {
                //TODO:
            }

        }


    }


}
