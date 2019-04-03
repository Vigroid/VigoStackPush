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

    val userList = listOf(User("vigo", listOf(Addr("1 rd"), Addr("524 broadway"))), User("test", listOf(Addr("4 st"), Addr("666 ave"))))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            Observable
                    .fromIterable(userList)
                    .flatMap { usr -> Observable.fromIterable(usr.addrs) }
                    .subscribe { addr ->
                        logInfo(addr.street)
                    }
        }

        button2.setOnClickListener {
            Observable
                    .fromIterable(userList)
                    .map { usr -> usr.addrs }
                    .subscribe { addrs ->
                        for (addr in addrs) {
                            logInfo(addr.street)
                        }
                    }
        }

        btn_web.setOnClickListener {
            startActivity(Intent(this@MainActivity, WebViewActivity::class.java))
        }
    }

    private fun logInfo(info: String, tag: String = "abc") {
        Log.i(tag, info)
    }
}
