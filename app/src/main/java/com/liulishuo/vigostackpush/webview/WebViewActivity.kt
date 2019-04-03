package com.liulishuo.vigostackpush.webview

import android.app.Activity
import android.os.Bundle
import android.webkit.WebViewClient
import com.liulishuo.vigostackpush.R
import kotlinx.android.synthetic.main.activity_web.*

class WebViewActivity:Activity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

        web.webViewClient = WebViewClient()

        web.loadUrl("file:///android_asset/demo.html")
    }
}