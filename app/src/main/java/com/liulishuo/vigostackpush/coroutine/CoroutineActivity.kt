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

        btn_test.setOnClickListener {
            runBlocking {
                logInfo("The answer is ${concurrentSum()}")
            }
        }

    }

    private suspend fun function1(): Int {
        delay(1000)
        return 13
    }

    private suspend fun function2(): Int {
        delay(1000)
        return 29
    }

    private suspend fun concurrentSum(): Int = coroutineScope {
        val one = async { function1() }
        val two = async { function2() }

        one.await() + two.await()
    }

}