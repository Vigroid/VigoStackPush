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
                // launch a coroutine to process some kind of incoming request
                val request = launch {
                    // it spawns two other jobs, one with GlobalScope
                    GlobalScope.launch {
                        logInfo("job1: I run in GlobalScope and execute independently!")
                        delay(1000)
                        logInfo("job1: I am not affected by cancellation of the request")
                    }
                    // and the other inherits the parent context
                    launch {
                        delay(100)
                        logInfo("job2: I am a child of the request coroutine")
                        delay(1000)
                        logInfo("job2: I will not execute this line if my parent request is cancelled")
                    }
                }
                delay(500)
                request.cancel() // cancel processing of the request
                delay(1000) // delay a second to see what happens
                logInfo("main: Who has survived request cancellation?")
            }
        }

    }
}