package jalilurrahman.com.lifecyclekotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import jalilurrahman.com.lifecyclekotlin.locationManager.LocationManagerActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun LocationManagerAction(view:View) {
        var intent = Intent(this@MainActivity,LocationManagerActivity::class.java)
        startActivity(intent)
    }
}
