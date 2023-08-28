package com.maschion.mywebview

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myWebView: WebView = findViewById(R.id.webview)
        myWebView.webViewClient = MyWebViewClient(this)
        //inserir o link do site aqui
        myWebView.loadUrl("https://...")
        myWebView.settings.javaScriptEnabled = true

    }
}

class MyWebViewClient(private val context: Context) : WebViewClient() {
    override fun shouldOverrideUrlLoading(
        view: WebView,
        request: WebResourceRequest
    ): Boolean {

        val requestUrl = request.url.toString()
        if (requestUrl.startsWith("whatsapp:")) {
            try {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(requestUrl)
                context.startActivity(intent)
                return true
            } catch (e: Exception) {
                Toast.makeText(context, "Por favor instalar o whatsapp", Toast.LENGTH_LONG).show()
            }
        }
        return false
    }
}