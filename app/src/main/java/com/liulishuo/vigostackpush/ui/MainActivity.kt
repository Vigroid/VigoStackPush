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
                    .just(1, 2, 4, 6, 7, 8)
                    .all { it < 10 }
                    .subscribe({ logInfo("$it") }, {})
        }

        button2.setOnClickListener {
            Observable
                    .just(1, 2, 4, 6, 7, 8)
                    .contains(7)
                    .subscribe({ logInfo("$it") }, {})
        }

        button3.setOnClickListener {
            Observable
                    .ambArray(
                            Observable
                                    .just(1, 2, 3)
                                    .delay(1, TimeUnit.SECONDS),
                            Observable
                                    .just(100, 200, 900))
                    .subscribe { logInfo("$it") }
        }
    }

    private fun logInfo(info: String?, tag: String = "abc") {
        Log.i(tag, info)
    }
}
