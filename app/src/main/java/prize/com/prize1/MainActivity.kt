package prize.com.prize1

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
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
        }
    }

    private fun showWeb(angle: Int) {
        val url: String = et_url.text.toString()

        WebActivity.go(this, url, angle)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_go1.setOnClickListener(this)
        btn_go2.setOnClickListener(this)
        btn_go3.setOnClickListener(this)
        btn_go4.setOnClickListener(this)
    }
}
