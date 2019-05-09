package com.liulishuo.vigostackpush.coroutine

import android.app.Activity
import android.os.Bundle
import com.liulishuo.core.logInfo
import com.liulishuo.vigostackpush.R
import kotlinx.android.synthetic.main.activity_coroutine.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

class CoroutineActivity:Activity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine)

        btn_test.setOnClickListener {
            GlobalScope.launch {
                // 启动一个新协程并停滞一秒
                delay(1000L)
                logInfo("world")
            }

            thread {
                // 启动一个新线程并停滞二秒
                Thread.sleep(2000L)
                logInfo("!")
            }

            logInfo("Hello, ")
        }
    }
}