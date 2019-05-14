package com.liulishuo.vigostackpush.coroutine

import android.app.Activity
import android.os.Bundle
import com.liulishuo.core.logInfo
import com.liulishuo.vigostackpush.R
import kotlinx.android.synthetic.main.activity_coroutine.*
import kotlinx.coroutines.*

class CoroutineActivity : Activity() {
    @UseExperimental(ObsoleteCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine)

        btn_test.setOnClickListener {
            runBlocking {
                // will run block in main thread
                launch {
                    logInfo("main runBlocking      : I'm working in thread ${Thread.currentThread().name}")
                }

                // will run block in default thread, equals to GlobalScope.launch { ... }
                launch(Dispatchers.Default) {
                    logInfo("Default runBlocking      : I'm working in thread ${Thread.currentThread().name}")
                }

                // will run block in main thread
                launch(Dispatchers.Unconfined) {
                    logInfo("Unconfined runBlocking      : I'm working in thread ${Thread.currentThread().name}")
                }

                // will run in new thread
                launch(newSingleThreadContext("vigroidThread")) {
                    logInfo("newThread runBlocking      : I'm working in thread ${Thread.currentThread().name}")
                }
            }
        }

    }
}