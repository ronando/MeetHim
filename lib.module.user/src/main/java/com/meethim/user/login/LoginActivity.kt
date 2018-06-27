package com.meethim.user.login

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.avos.avoscloud.AVException
import com.avos.avoscloud.AVObject
import com.avos.avoscloud.SaveCallback
import com.meethim.user.R
import com.meethim.user.data.source.remote.LoginSNS

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        testLeanCloud()
    }


    fun login(view: View) {
        LoginSNS.loginWithQq(this)
    }


    private fun testLeanCloud() {
        // 测试 SDK 是否正常工作的代码
        val testObject = AVObject("TestObject")
        testObject.put("words", "Hello World-user!")
        testObject.saveInBackground(object : SaveCallback() {
            override fun done(e: AVException?) {
                if (e == null) {
                    Log.d("saved", "success!")
                }
            }
        })
    }

}
