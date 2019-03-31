package com.liulishuo.vigostackpush.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.liulishuo.vigostackpush.R
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {

    class MyCallable : Callable<String> {
        override fun call(): String {
            Thread.sleep(5000)
            return "ojbk"
        }

    }

    private val threadPool = Executors.newCachedThreadPool()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        start.setOnClickListener {
            val stringFuture = threadPool.submit(MyCallable())

            Observable
                    .fromFuture(stringFuture, 2, TimeUnit.SECONDS)
                    .subscribe({ s -> logInfo(s) }, { t -> logInfo(t.toString()) }, { logInfo("on completed") })
        }
    }

    private fun logInfo(info: String?, tag: String = "abc") {
        Log.i(tag, info)
    }
}
