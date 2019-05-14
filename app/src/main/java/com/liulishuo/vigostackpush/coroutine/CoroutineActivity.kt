package com.liulishuo.vigostackpush.coroutine

import android.app.Activity
import android.os.Bundle
import com.liulishuo.vigostackpush.R
import kotlinx.android.synthetic.main.activity_coroutine.*
import kotlinx.coroutines.*

class CoroutineActivity : Activity() {
    @UseExperimental(ObsoleteCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine)

        btn_test.setOnClickListener {
            newSingleThreadContext("Ctx1").use { ctx1 ->
                newSingleThreadContext("Ctx2").use { ctx2 ->
                    runBlocking(ctx1) {
                        log("Started in ctx1, ${coroutineContext[Job]}")
                        withContext(ctx2) {
                            log("Working in ctx2, ${coroutineContext[Job]}")
                        }
                        log("Back to ctx1, ${coroutineContext[Job]}")
                    }
                }
            }
        }

    }

    fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")
}