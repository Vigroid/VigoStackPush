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
                launch {
                    delay(200)
                    logInfo("runblocking co1 -200")
                }

                coroutineScope {
                    launch {
                        delay(500)
                        logInfo("nested scope- co2 - 500 ")
                    }
                    logInfo("nested scope -0")
                }
                logInfo("runblocking")
            }
            logInfo("runblocking over")
        }
    }
}