package com.liulishuo.vigostackpush.coroutine

import android.app.Activity
import android.os.Bundle
import com.liulishuo.core.logInfo
import com.liulishuo.vigostackpush.R
import kotlinx.android.synthetic.main.activity_coroutine.*
import kotlinx.coroutines.*

class CoroutineActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine)

        var job: Job?
        btn_test.setOnClickListener {
            runBlocking {
                withTimeout(3000) {
                    repeat()
                }
                logInfo("yo")
            }
        }
    }

    private suspend fun repeat() {
        repeat(1000) {
            logInfo("$it")
            delay(500)
        }
    }
}