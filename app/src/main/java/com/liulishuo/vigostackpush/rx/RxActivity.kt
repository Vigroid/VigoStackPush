package com.liulishuo.vigostackpush.rx

import android.app.Activity
import android.os.Bundle
import com.liulishuo.core.logInfo
import com.liulishuo.vigostackpush.R
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_rx.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class RxActivity :Activity(){
    val sdf = SimpleDateFormat("HH:mm:ss")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx)

        val source = Observable.interval(1, TimeUnit.SECONDS).take(10).replay()

        source.connect()

        button.setOnClickListener {
            source.subscribe { logInfo("subscriber 1 - $it - time: ${sdf.format(Date())}") }
            source.delaySubscription(3, TimeUnit.SECONDS)
                    .subscribe { logInfo("subscriber 2 - $it - time: ${sdf.format(Date())}") }
        }
    }
}