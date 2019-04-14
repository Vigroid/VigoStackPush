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

        button.setOnClickListener {
            Observable
                    .interval(500, TimeUnit.MILLISECONDS)
                    .take(10)
                    .elementAt(9)
                    .subscribe {logInfo("$it")}
        }

        button2.setOnClickListener {
            Observable
                    .interval(500, TimeUnit.MILLISECONDS)
                    .take(10)
                    .ignoreElements()
                    .subscribe{logInfo("completed")}
        }
    }

    private fun logInfo(info: String?, tag: String = "abc") {
        Log.i(tag, info)
    }
}
