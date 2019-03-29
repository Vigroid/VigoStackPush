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

        button.setOnClickListener { view ->
            Observable
                    .just("Hello")
                    .doOnNext { logInfo("doOnNext(): $it") }
                    .doAfterNext { logInfo("doAfterNext(): $it") }
                    .doOnComplete { logInfo("doOnComplete()") }
                    .doOnSubscribe { logInfo("doOnSubscribe(): ${it.isDisposed}") }
                    .doAfterTerminate { logInfo("doAfterTerminate()") }
                    .doFinally { logInfo("doFinally():") }
                    .doOnEach { logInfo("doOnEach(): $it") }
                    .doOnLifecycle({ logInfo("doOnLifecycle(): ${it.isDisposed}") }, { logInfo("doOnLifecycle(): run") })
                    .map { "$it world" }
                    .subscribe({ logInfo("收到 $it") }, {}, { logInfo("received onCompleted") }, { logInfo("received onSubscribe") })
        }
    }

    private fun logInfo(info: String) {
        Log.i("vigo", info)
    }
}
