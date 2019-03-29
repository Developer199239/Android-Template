package jalilurrahman.com.lifecycleaware1;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

public class ActivitylifecycleawareMain implements LifecycleObserver {
    static final ActivitylifecycleawareMain outInstance = new ActivitylifecycleawareMain();

    public static ActivitylifecycleawareMain getInstance() {
        return outInstance;
    }

    private ActivitylifecycleawareMain() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void LibOnStart() {
        Log.d("tag",
                "Called From ActivitylifecycleawareMain Class, called onStart() of Activity : ON_START");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void TimeForBusinesswhileOnPause() {
        Log.d("tag",
                "Called From ActivitylifecycleawareMain Class, called onPause() of Activity : ON_PAUSE");
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void TimeForBusinesswhileOnStop() {
        Log.d("tag",
                "Called FromActivitylifecycleawareMain Class, called onStop() of Activity : ON_STOP");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void TimeForBusinesswhileOnResume() {
        Log.d("tag",
                "Called From ActivitylifecycleawareMain Class, called onResume() of Activity : ON_RESUME");
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void TimeForBusinesswhileOnDestroy() {
        Log.d("tag",
                "Called From ActivitylifecycleawareMain Class, called onDestroy() of Activity : ON_DESTROY");
    }

}
