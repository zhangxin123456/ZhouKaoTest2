package jiyun.com.zhoukaotest2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by lx on 2017/5/3.
 */

public class WebViewActivity extends Activity {
    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weiviewmoban);
        mWebView = (WebView) findViewById(R.id.webView);
        Intent in = getIntent();
        String url = in.getStringExtra("url");
        mWebView.loadUrl(url);
        initViews();
    }

    private void initViews() {
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;    //返回值是true的时候控制网页在WebView中去打开，如果为false调用下同浏览器或第三方浏览器去打开
            }
        });
    }
}
