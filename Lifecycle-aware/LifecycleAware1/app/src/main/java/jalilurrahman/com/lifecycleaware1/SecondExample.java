package jalilurrahman.com.lifecycleaware1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class SecondExample extends AppCompatActivity {

    private String TAG = "1234_"+SecondExample.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_example);

        Log.d(TAG,"Lifecycle state is : "+getLifecycle().getCurrentState());
        Log.i(TAG, "Owner onCreate Called");

        getLifecycle().addObserver(new MainObserver(getLifecycle()));
    }

     public void onStart() {
        super.onStart();

        Log.i(TAG, "Owner onStart Called");
    }

    public void onResume() {
        super.onResume();

        Log.i(TAG, "Owner onResume Called");
    }

    public void onPause() {
        super.onPause();

        Log.i(TAG, "Owner onPause Called");
    }

    public void onStop() {
        super.onStop();

        Log.i(TAG, "Owner onStop Called");
    }

    public void onDestroy() {
        super.onDestroy();

        Log.i(TAG, "Owner onDestroy Called");
    }
}
