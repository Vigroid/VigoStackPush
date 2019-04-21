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
                    .join(source2,
                            //source1的时间窗口，这个时间窗口内的source2发过来的数据都组合起来
                            Function<Int,Observable<Int>> { integer-> Observable.just(integer).delay(200, TimeUnit.MILLISECONDS)},
                            //source2的时间窗口，这个时间窗口内的source1发过来的数据都组合起来
                            Function<Int,Observable<Int>> { integer-> Observable.just(integer).delay(200, TimeUnit.MILLISECONDS)},
                            BiFunction<Int, Int,String>{integer1, integer2 -> "$integer1:$integer2"})
                    .subscribe { logInfo(it) }
        }

        button2.setOnClickListener {
            Observable
                    .combineLatest(source1, source2,
                            BiFunction<Int, Int, String> { t1, t2 -> "$t1:$t2" })
                    .subscribe { logInfo(it) }
        }
    }

    private fun logInfo(info: String?, tag: String = "abc") {
        Log.i(tag, info)
    }
}
