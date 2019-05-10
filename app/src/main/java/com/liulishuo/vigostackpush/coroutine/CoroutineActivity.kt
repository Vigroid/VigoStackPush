package com.liulishuo.vigostackpush.coroutine

import android.app.Activity
import android.os.Bundle
import com.liulishuo.core.logInfo
import com.liulishuo.vigostackpush.R
import kotlinx.android.synthetic.main.activity_coroutine.*
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

class CoroutineActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine)

        btn_test.setOnClickListener {
            runBlocking {
                val time = measureTimeMillis {
                    val one = async(start = CoroutineStart.LAZY) { function1() }
                    val two = async(start = CoroutineStart.LAZY) { function2() }

                    one.start()
                    two.start()
                    logInfo("The answer is ${one.await() + two.await()}")
                }
                logInfo("time cost is $time")
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
}