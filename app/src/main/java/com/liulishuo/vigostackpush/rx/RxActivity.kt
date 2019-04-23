package com.liulishuo.vigostackpush.rx

import android.app.Activity
import android.os.Bundle
import com.liulishuo.core.logInfo
import com.liulishuo.vigostackpush.R
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_rx.*

class RxActivity :Activity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx)

        val source1 = Observable.just(1,2,3)
        val source2 = Observable.just(4,5,6)

        button.setOnClickListener {
            source1
                    .startWith(source2)
                    .startWith(Int.MIN_VALUE)
                    .subscribe { logInfo(it) }
        }
    }
}