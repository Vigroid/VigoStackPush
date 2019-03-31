package com.liulishuo.vigostackpush.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.liulishuo.vigostackpush.R
import com.liulishuo.vigostackpush.rx.HotnColdObservaleDemo
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private var observable: Observable<Long> = subscribeHot()
    private var disposables: MutableList<Disposable> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            clearDisposable()
            observable = subscribeCold()
            Toast.makeText(this@MainActivity, "switch to cold", Toast.LENGTH_SHORT).show()
        }

        button2.setOnClickListener {
            clearDisposable()
            observable = subscribeHot()
            Toast.makeText(this@MainActivity, "switch to hot", Toast.LENGTH_SHORT).show()
        }

        start.setOnClickListener {
            disposables.add(observable.subscribe { logInfo(it.toString()) })
            disposables.add(observable.subscribe { logInfo(it.toString(), "xyz") })
        }
    }

    private fun logInfo(info: String, tag: String = "abc") {
        Log.i(tag, info)
    }

    private fun subscribeCold(): Observable<Long> {
        return HotnColdObservaleDemo
                .getColdObservable()
    }

    private fun subscribeHot(): Observable<Long> {
        val observable = HotnColdObservaleDemo
                .getHotObservable()
        observable.connect()

        return observable
    }

    private fun subscribeHotByPublishSubject(): Observable<Long> {
        val publishSubject = PublishSubject.create<Long>()
        //publishSubject做中转，cold observable转hot
        HotnColdObservaleDemo
                .getColdObservable()
                .subscribe(publishSubject)
        return publishSubject
    }

    private fun clearDisposable(){
        for (disposable in disposables){
            disposable.dispose()
        }

        disposables.clear()
    }
}
