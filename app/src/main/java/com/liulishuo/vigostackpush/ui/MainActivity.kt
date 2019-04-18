package com.liulishuo.vigostackpush.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.liulishuo.vigostackpush.R
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val source1 = Observable.intervalRange(0, 9, 0, 1, TimeUnit.SECONDS)
        val source2 = Observable.intervalRange(100, 9, 1, 1, TimeUnit.SECONDS)

        button.setOnClickListener {
            Observable
                    .merge(source1, source2)
                    .subscribe { logInfo("$it") }
        }

        button2.setOnClickListener {
            Observable
                    .zip(source1, source2,
                            BiFunction<Long, Long, Long> { t1, t2 -> t1 + t2 })
                    .subscribe { logInfo("$it") }
        }
    }

    private fun logInfo(info: String?, tag: String = "abc") {
        Log.i(tag, info)
    }
}
