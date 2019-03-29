package com.liulishuo.vigostackpush.rx

import io.reactivex.Observable
import io.reactivex.observables.ConnectableObservable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class HotnColdObservaleDemo {
    companion object {
        fun getHotObservable(): ConnectableObservable<Long> {
            return getColdObservable().publish()
        }

        fun getColdObservable(): Observable<Long> {
            return Observable
                    .create<Long> { emitter ->
                        Observable
                                .interval(10, TimeUnit.MILLISECONDS, Schedulers.computation())
                                .take(Long.MAX_VALUE)
                                .subscribe { emitter.onNext(it) }
                    }.observeOn(Schedulers.newThread())
        }
    }
}