package jalilurrahman.com.lifecyclekotlin.locationManager

import android.Manifest
import androidx.lifecycle.LifecycleObserver
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import jalilurrahman.com.lifecyclekotlin.R
import kotlinx.android.synthetic.main.activity_location_manager.*

class LocationManagerActivity : AppCompatActivity() {

    private lateinit var myLocationManager : MyLocationManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_manager)

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_LOCATION_PERMISSION_CODE)
        } else {
            setupLocationListener()
        }
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {

        } else {
            Toast.makeText(this@LocationManagerActivity,"Permission deny!",Toast.LENGTH_LONG).show()
        }
    }

    private fun setupLocationListener(){
        myLocationManager = MyLocationManager(this@LocationManagerActivity){
            location->
            textView.text = location.latitude.toString()+", "+location.longitude.toString()
        }
        lifecycle.addObserver(myLocationManager as LifecycleObserver)
    }

    companion object {
        const val REQUEST_LOCATION_PERMISSION_CODE = 1
    }

}
