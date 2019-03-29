package com.liulishuo.vigostackpush.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.liulishuo.vigostackpush.R
import com.liulishuo.vigostackpush.rx.HotnColdObservaleDemo
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            subscribeCold()
        }

        button2.setOnClickListener {
            subscribeHot()
        }
    }

    private fun logInfo(info: String, tag: String = "abc") {
        Log.i(tag, info)
    }

    private fun subscribeCold() {
        val observable = HotnColdObservaleDemo
                .getColdObservable()
        observable.subscribe { logInfo(it.toString()) }
        observable.subscribe { logInfo(it.toString(), "xyz") }
    }

    private fun subscribeHot() {
        val observable = HotnColdObservaleDemo
                .getHotObservable()
        observable.connect()
        observable.subscribe { logInfo(it.toString()) }
        observable.subscribe { logInfo(it.toString(), "xyz") }
    }
}
