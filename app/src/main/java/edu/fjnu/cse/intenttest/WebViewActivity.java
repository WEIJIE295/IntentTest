package edu.fjnu.cse.intenttest;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.app.ProgressDialog;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;

public class WebViewActivity extends AppCompatActivity {

    WebView webview;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_web_view);

        Intent intent = getIntent();
        Uri uri = intent.getData();

        pd=new ProgressDialog(this);
        pd.setMessage("正在加载...");


        //webview的简单设置
        webview = (WebView) findViewById(R.id.show);
        webview.loadUrl(uri.toString());
        WebSettings websettings=webview.getSettings();
        websettings.setSupportZoom(true);
        websettings.setBuiltInZoomControls(true);
        webview.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                pd.show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                pd.dismiss();
            }
        });

    }

    //后退键
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK&&webview.canGoBack()){
            webview.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    //菜单键
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 0, 0, "刷新");
        menu.add(0, 0, 1, "后退");
        menu.add(0, 0, 2, "前进");
        return super.onCreateOptionsMenu(menu);
    }
    //菜单点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getOrder()) {
            case 0:
                webview.reload();
                break;
            case 1:
                if(webview.canGoBack()){
                    webview.goBack();
                }
                break;
            case 2:
                if(webview.canGoForward()){
                    webview.goForward();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
