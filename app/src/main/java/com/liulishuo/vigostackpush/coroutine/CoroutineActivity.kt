package com.liulishuo.vigostackpush.coroutine

import android.app.Activity
import android.os.Bundle
import com.liulishuo.core.logInfo
import com.liulishuo.vigostackpush.R
import kotlinx.android.synthetic.main.activity_coroutine.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.concurrent.thread

class CoroutineActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine)

        btn_test.setOnClickListener {
            runBlocking {
                launch {
                    delay(1000)
                    logInfo("World!")
                }
                logInfo("Hello, ")
                //在这里不需要job.join()或者是Thread.sleep()或是delay()，
                // 因为runBlocking这个外部coroutineScope只有在内部所有coroutineScope都结束才结束。
            }
        }
    }
}