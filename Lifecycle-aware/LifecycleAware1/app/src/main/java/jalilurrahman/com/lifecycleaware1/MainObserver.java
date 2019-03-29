package jalilurrahman.com.lifecycleaware1;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

public class MainObserver implements LifecycleObserver {

    private String TAG = "1234_"+MainObserver.class.getSimpleName();

    private Lifecycle lifecycle;
    public MainObserver(Lifecycle life) {
        this.lifecycle = life;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate() {
        Log.i(TAG, "Observer onCreate Called");
        Log.i(TAG, "Lifecycle State is : ${lifecycle.currentState}");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {
        Log.i(TAG, "Observer onStart Called");
        Log.i(TAG, "Lifecycle State is : ${lifecycle.currentState}");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        Log.i(TAG, "Observer onResume Called");
        Log.i(TAG, "Lifecycle State is : ${lifecycle.currentState}");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        Log.i(TAG, "Observer onPause Called");
        Log.i(TAG, "Lifecycle State is : ${lifecycle.currentState}");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {
        Log.i(TAG, "Observer onStop Called");
        Log.i(TAG, "Lifecycle State is : ${lifecycle.currentState}");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        Log.i(TAG, "Observer onDestroy Called");
        Log.i(TAG, "Lifecycle State is : ${lifecycle.currentState}");
    }


}
