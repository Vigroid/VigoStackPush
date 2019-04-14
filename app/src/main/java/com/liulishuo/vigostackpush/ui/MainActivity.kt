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
                    .range(1, 11)
                    .first(1)
                    .subscribe({logInfo(it.toString())},{})
        }

        button2.setOnClickListener {
            Observable
                    .range(1, 11)
                    .last(11)
                    .subscribe({logInfo(it.toString())},{})
        }
    }

    private fun logInfo(info: String?, tag: String = "abc") {
        Log.i(tag, info)
    }
}
