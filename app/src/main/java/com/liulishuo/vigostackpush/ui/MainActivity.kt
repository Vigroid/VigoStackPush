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
                    .just(1, 2, 2, 3, 3, 4, 5, 1, 1, 9)
                    .distinct()
                    .subscribe { logInfo("$it") }
        }

        button2.setOnClickListener {
            Observable
                    .just(1, 2, 2, 3, 3, 4, 5, 1, 1, 9)
                    .distinctUntilChanged()
                    .subscribe { logInfo("$it") }
        }

        button3.setOnClickListener {
            Observable
                    .just(1, 2, 2, 3, 3, 4, 5, 1, 1, 9)
                    .filter { num -> num >= 3 }
                    .subscribe { logInfo("$it") }
        }
    }

    private fun logInfo(info: String?, tag: String = "abc") {
        Log.i(tag, info)
    }
}
