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
                    .intervalRange(0,10,1,1, TimeUnit.SECONDS)
                    .skip(5)
                    .subscribe {logInfo("$it")}
        }

        button2.setOnClickListener {
            Observable
                    .intervalRange(0,10,1,1, TimeUnit.SECONDS)
                    .skipLast(3, TimeUnit.SECONDS)
                    .subscribe {logInfo("$it")}
        }
    }

    private fun logInfo(info: String?, tag: String = "abc") {
        Log.i(tag, info)
    }
}
