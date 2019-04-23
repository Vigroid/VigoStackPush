package com.liulishuo.vigostackpush.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.liulishuo.vigostackpush.R
import com.liulishuo.vigostackpush.coroutine.CoroutineActivity
import com.liulishuo.vigostackpush.rx.RxActivity
import com.liulishuo.vigostackpush.webview.WebViewActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_rx.setOnClickListener {
            startActivity(Intent(this@MainActivity, RxActivity::class.java))
        }

        btn_coroutine.setOnClickListener {
            startActivity(Intent(this@MainActivity, CoroutineActivity::class.java))
        }

        btn_web.setOnClickListener {
            startActivity(Intent(this@MainActivity, WebViewActivity::class.java))
        }
    }
}
