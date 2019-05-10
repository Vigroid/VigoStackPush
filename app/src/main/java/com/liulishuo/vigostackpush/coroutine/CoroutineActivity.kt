package com.liulishuo.vigostackpush.coroutine

import android.app.Activity
import android.os.Bundle
import com.liulishuo.core.logInfo
import com.liulishuo.vigostackpush.R
import kotlinx.android.synthetic.main.activity_coroutine.*
import kotlinx.coroutines.*
import kotlin.concurrent.thread

class CoroutineActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine)

        btn_test.setOnClickListener {
            runBlocking {
                // coroutine十分轻量，同时开100_000个也没问题
                repeat(100_000){
                    launch {
                        delay(1000)
                        logInfo(".")
                    }
                }
            }
        }
    }
}