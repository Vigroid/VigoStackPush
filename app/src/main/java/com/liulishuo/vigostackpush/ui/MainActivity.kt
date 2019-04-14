package com.liulishuo.vigostackpush.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.liulishuo.vigostackpush.R
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            Observable
                    .empty<Int>()
                    .defaultIfEmpty(8)
                    .subscribe { logInfo("$it") }
        }

        button2.setOnClickListener {
            Observable
                    .empty<Int>()
                    .switchIfEmpty (Observable.just(1,2,3))
                    .subscribe { logInfo("$it") }
        }
    }

    private fun logInfo(info: String?, tag: String = "abc") {
        Log.i(tag, info)
    }
}
