package jalilurrahman.com.lifecyclekotlin.locationManager

import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

@SuppressWarnings("MissingPermission")
class MyLocationManager(private val context: Context, private val callback:(Location)->Unit) : LifecycleObserver {

    private val TAG = "1234_"
    private var mLocationManager: LocationManager? = null

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun start(){
        mLocationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager?
        mLocationManager?.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, locationListener)

        // Force an update with the last location, if available.
        val lastLocation = mLocationManager?.getLastKnownLocation(
            LocationManager.GPS_PROVIDER
        )
        if (lastLocation != null) {
            locationListener.onLocationChanged(lastLocation)
        }
        Log.d(TAG,"MyLocationMangager started")
        Toast.makeText(context, "MyLocationManager started", Toast.LENGTH_SHORT).show()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun stop() {
        if (mLocationManager == null) {
            return
        }
        mLocationManager?.removeUpdates(locationListener)
        mLocationManager = null
        Log.d(TAG,"MyLocationManger paused")
        Toast.makeText(context, "MyLocationManager paused", Toast.LENGTH_SHORT).show()
    }

    val locationListener = object : LocationListener {
        override fun onLocationChanged(location: Location?) {
            Log.d(TAG,"Location found!")
            callback.invoke(location!!)
        }

        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onProviderEnabled(provider: String?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onProviderDisabled(provider: String?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }




}