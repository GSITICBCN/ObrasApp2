package com.obrasapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient

class WebArgs : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_args)

        //Enlace a sitio web
        val wvArgs : WebView = findViewById(R.id.wv_args)
        wvArgs.settings.javaScriptEnabled = true
        wvArgs.loadUrl("https://www.args.cat")

    }
}