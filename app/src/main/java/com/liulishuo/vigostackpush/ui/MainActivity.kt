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
                    .create<Int> {
                        for (i in 1..10) {
                            Thread.sleep(i * 100L)
                            it.onNext(i)
                        }
                        it.onComplete()
                    }
                    .debounce(500, TimeUnit.MILLISECONDS)
                    .subscribe { logInfo("$it") }
        }

        button2.setOnClickListener {
        }
    }

    private fun logInfo(info: String?, tag: String = "abc") {
        Log.i(tag, info)
    }
}
