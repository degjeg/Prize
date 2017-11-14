package prize.com.prize1

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        var angle: Int = 0
    }


    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btn_go1 -> {
                showWeb(0)
            }
            R.id.btn_go2 -> {
                showWeb(90)
            }
            R.id.btn_go3 -> {
                showWeb(180)
            }
            R.id.btn_go4 -> {
                showWeb(270)
            }

            R.id.btn_go5 -> {
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_NOSENSOR
                //强制指定纵向 value为5

                // ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                // ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE
                // ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                // ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT

                when (angle) {
                    0 -> requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                    1 -> requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE
                    2 -> requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                    3 -> requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT
                }
                angle = (++angle) % 4
                Log.e("MainActivity", "angle:"+angle)
                //强制指定反方向纵向 value为9
            }
        }
    }

    private fun showWeb(angle: Int) {
        val url: String = et_url.text.toString()

        WebActivity.go(this, url, angle)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.e("MainActivity", "onCreate:"+angle)

        setContentView(R.layout.activity_main)

        btn_go1.setOnClickListener(this)
        btn_go2.setOnClickListener(this)
        btn_go3.setOnClickListener(this)
        btn_go4.setOnClickListener(this)
        btn_go5.setOnClickListener(this)

        // showWeb(270)
        // finish()
    }

    override fun onResume() {
        super.onResume()



    }
}
