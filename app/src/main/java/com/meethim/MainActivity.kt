package com.meethim

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.avos.avoscloud.AVException
import com.avos.avoscloud.AVObject
import com.avos.avoscloud.SaveCallback
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Example of a call to a native method
        sample_text.text = stringFromJNI()

        testLeanCloud()
    }

     private fun testLeanCloud() {
        // 测试 SDK 是否正常工作的代码
        val testObject = AVObject("TestObject")
        testObject.put("words", "Hello World!")
        testObject.saveInBackground(object : SaveCallback() {
            override fun done(e: AVException?) {
                if (e == null) {
                    Log.d("saved", "success!")
                }
            }
        })
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    companion object {

        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }
}
