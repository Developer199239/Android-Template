package jalilurrahman.com.kakao_espresso_sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_update.setOnClickListener {
            text_view_hello.text = "Hello Kakao World!"
            image_view_kakao.visibility = View.VISIBLE
        }
    }
}
