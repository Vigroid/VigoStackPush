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
            subscribeCold()
        }
    }

    private fun logInfo(info: String) {
        Log.i("vigo", info)
    }

    private fun subscribeCold(){
        HotnColdObservaleDemo
                .getColdObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { logInfo(it.toString()) }
    }
}
