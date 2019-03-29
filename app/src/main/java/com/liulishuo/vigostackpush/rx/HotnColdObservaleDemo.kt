package com.liulishuo.vigostackpush.rx

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class HotnColdObservaleDemo{
    companion object {
        fun getHotObservable(): Observable<Long>{
            return Observable.just(1)
        }

        fun getColdObservable(): Observable<Long>{
            return Observable.create<Long> {emitter->
                Observable
                        .interval(1, TimeUnit.SECONDS, Schedulers.computation())
                        .take(Long.MAX_VALUE)
                        .subscribe { emitter.onNext(it) }
            }.observeOn(Schedulers.newThread())
        }
    }
}