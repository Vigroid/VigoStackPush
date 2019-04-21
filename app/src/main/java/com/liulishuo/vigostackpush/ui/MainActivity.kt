package com.liulishuo.vigostackpush.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.liulishuo.vigostackpush.R
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val source1 = Observable.just(1,2,3)
        val source2 = Observable.just(4,5,6)

        button.setOnClickListener {
            source1
                    .startWith(source2)
                    .startWith(Int.MIN_VALUE)
                    .subscribe { logInfo(it) }
        }

    }

    private fun logInfo(info: Any?, tag: String = "abc") {
        Log.i(tag, "$info")
    }
}
