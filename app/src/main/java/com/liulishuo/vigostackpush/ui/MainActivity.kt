package com.liulishuo.vigostackpush.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.liulishuo.vigostackpush.R
import com.liulishuo.vigostackpush.rx.model.Addr
import com.liulishuo.vigostackpush.rx.model.User
import com.liulishuo.vigostackpush.webview.WebViewActivity
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            Observable
                    .range(1, 8)
                    .groupBy { return@groupBy if (it % 2 == 0) "奇数" else "偶数" }
                    .subscribe { logInfo(it.key) }
        }

        button2.setOnClickListener {
            Observable
                    .range(1, 8)
                    .groupBy { return@groupBy if (it % 2 == 0) "奇数" else "偶数" }
                    .subscribe { newSource ->
                        if (newSource.key == "奇数"){
                            newSource.subscribe { logInfo("我是奇数欧 $it") }
                        } else{
                            newSource.subscribe { logInfo("我是偶数 $it", "xyz") }
                        }
                    }
        }

        btn_web.setOnClickListener {
            startActivity(Intent(this@MainActivity, WebViewActivity::class.java))
        }
    }

    private fun logInfo(info: String?, tag: String = "abc") {
        Log.i(tag, info)
    }
}
