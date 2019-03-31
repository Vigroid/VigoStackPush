package com.liulishuo.vigostackpush.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.liulishuo.vigostackpush.R
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        start.setOnClickListener {
            Observable
                    .range(0, 9)
                    .repeatWhen { Observable.timer(10, TimeUnit.SECONDS) }
                    .subscribe({ s -> logInfo(s.toString()) }, { t -> logInfo(t.toString()) }, { logInfo("on completed") })
        }
    }

    private fun logInfo(info: String, tag: String = "abc") {
        Log.i(tag, info)
    }
}
