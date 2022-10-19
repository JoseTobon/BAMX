package mx.itesm.bamx

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentContainer


class HomeFragment : Fragment(R.layout.fragment_home) {

    lateinit var myWebView : WebView
    lateinit var carga : ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_home, container, false)

        myWebView = view.findViewById(R.id.webView)

        myWebView.settings.javaScriptEnabled = true

        var htmlPre = "<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"utf-8\"></head><body style='margin:0; pading:0; background-color: #243447;'>";
        var htmlCode =
        " <a class=\"twitter-timeline\" data-lang=\"es\" data-theme=\"dark\" href=\"https://twitter.com/BDAGDL?ref_src=twsrc%5Etfw\"></a> <script async src=\"https://platform.twitter.com/widgets.js\" charset=\"utf-8\"></script>"
        var htmlPost = "</body></html>";

        myWebView.loadDataWithBaseURL("null", htmlPre+htmlCode+htmlPost, "text/html", "UTF-8", null)

        /*
        myWebView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                carga.isVisible=false
                super.onPageFinished(view, url)
            }
        }*/


        return view
    }
}