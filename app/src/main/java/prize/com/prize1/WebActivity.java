package prize.com.prize1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.jetbrains.annotations.NotNull;

/**
 * Created by danger on 2017/11/13.
 */

public class WebActivity extends Activity {
    public static final String TAG = "WebActivity";

    WebView webView;

    int viewWidth, viewHeight;
    int angle;

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
        webView = findViewById(R.id.web_view);

        String url = getIntent().getStringExtra("url");
        angle = getIntent().getIntExtra("angle", 0);

        findViewById(R.id.h_scrollView).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                return true;
            }
        });

        webView.loadUrl(url);


        webView.post(() -> {
            viewWidth = root.getWidth();
            viewHeight = root.getHeight();

            Log.d(TAG, "angle" + webView.getRotation() + " (" + viewWidth + " x " + viewHeight);

            if (angle == 0) {
                webView.setRotation(0);
                webView.getLayoutParams().width = viewWidth;
                webView.getLayoutParams().height = viewHeight;
                webView.requestLayout();
                webView.setTranslationX(0);
                webView.setTranslationY(0);


            } else if (angle == 90) {
                webView.setRotation(90);
                webView.getLayoutParams().width = viewHeight;
                webView.getLayoutParams().height = viewWidth;
                webView.requestLayout();
                int diff = (viewHeight - viewWidth) / 2;
                webView.setTranslationX(-diff);
                webView.setTranslationY(diff);



            } else if (angle == 180) {

            } else if (angle == 270) {
                webView.setRotation(270);
                webView.getLayoutParams().width = viewHeight;
                webView.getLayoutParams().height = viewWidth;
                webView.requestLayout();
                int diff = (viewHeight - viewWidth) / 2;
                webView.setTranslationX(-diff);
                webView.setTranslationY(diff);

            }
            webView.post(() -> {
                        webView.loadUrl(url);
                        webView.getSettings().setJavaScriptEnabled(true);
                        webView.setWebViewClient(new WebViewClient() {
                            @Override
                            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                                webView.loadUrl(url);
                                return true;
                            }
                        });
                        webView.setWebChromeClient(new WebChromeClient());

                    }
            );
        });
    }


}
