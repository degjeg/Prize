package prize.com.prize1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ScrollView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

/**
 * Created by danger on 2017/11/13.
 */

public class WebActivity extends Activity {
    public static final String TAG = "WebActivity";

    WebView webView;

    int viewWidth, viewHeight;
    int angle;
    View webViewContainer;

    public static void go(Context context, @NotNull String url, int angle) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("angle", angle);

        context.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_web);
        View root = findViewById(R.id.rl);
        webViewContainer = findViewById(R.id.web_view);
        ScrollView scrollView = findViewById(R.id.h_scrollView);
        webView = findViewById(R.id.web_view);

        String url = getIntent().getStringExtra("url");
        angle = getIntent().getIntExtra("angle", 0);

        // findViewById(R.id.h_scrollView).setOnTouchListener(new View.OnTouchListener() {
        //     @Override
        //     public boolean onTouch(View arg0, MotionEvent arg1) {
        //         return true;
        //     }
        // });

        // webView.loadUrl(url);


        webView.post(() -> {
            View sizeView = findViewById(android.R.id.content);
            viewWidth = sizeView.getWidth();
            viewHeight = sizeView.getHeight();

            Toast.makeText(WebActivity.this, "屏幕尺寸：" + viewWidth + " x " + viewHeight, Toast.LENGTH_LONG).show();

            if (angle == 0) {
                webViewContainer.setRotation(0);
                webViewContainer.getLayoutParams().width = viewWidth;
                webViewContainer.getLayoutParams().height = viewHeight;
                webViewContainer.requestLayout();
                webViewContainer.setTranslationX(0);
                webViewContainer.setTranslationY(0);


            } else if (angle == 90) {
                webViewContainer.setRotation(90);
                webViewContainer.getLayoutParams().width = viewHeight;
                webViewContainer.getLayoutParams().height = viewWidth;
                webViewContainer.requestLayout();
                int diff = (viewHeight - viewWidth) / 2;
                webViewContainer.setTranslationX(-diff);
                webViewContainer.setTranslationY(diff);


            } else if (angle == 180) {

            } else if (angle == 270) {
                webViewContainer.setRotation(270);
                webViewContainer.getLayoutParams().width = viewHeight;
                webViewContainer.getLayoutParams().height = viewWidth;
                webViewContainer.requestLayout();
                int diff = (viewHeight - viewWidth) / 2;
                webViewContainer.setTranslationX(-diff);
                webViewContainer.setTranslationY(diff);

            }

            webView.post(() -> {
                        webView.setWebViewClient(new WebViewClient() {
                            @Override
                            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                                webView.loadUrl(url);
                                return true;
                            }
                        });
                        webView.setWebChromeClient(new WebChromeClient());
                        //声明WebSettings子类
                        WebSettings webSettings = webView.getSettings();

                        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
                        webSettings.setJavaScriptEnabled(true);
                        // 若加载的 html 里有JS 在执行动画等操作，会造成资源浪费（CPU、电量）
                        // 在 onStop 和 onResume 里分别把 setJavaScriptEnabled() 给设置成 false 和 true 即可

                        //支持插件
                        // webSettings.setPluginsEnabled(true);

                        //设置自适应屏幕，两者合用
                        //webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
                        //webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

                        //缩放操作
                        //webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
                        //webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
                        //webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件

                        //其他细节操作
                        //webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
                        //webSettings.setAllowFileAccess(true); //设置可以访问文件
                        //webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
                        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
                        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式

                        webView.getSettings().setJavaScriptEnabled(true);
                    }
            );

            webView.postDelayed(() -> {
                webView.loadUrl(url);
            }, 1);

            //
            // webView.postDelayed(() -> {
            //     webView.loadUrl("javascript:document.getElementById('loginName').value='hahaha'");
            // }, 6000);

        });
    }


}
