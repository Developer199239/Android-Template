package jalilurrahman.com.lifecycleaware1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getLifecycle().addObserver(ActivitylifecycleawareMain.getInstance());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getLifecycle().removeObserver(ActivitylifecycleawareMain.getInstance());
    }

    public void secondExample(View view){
        Intent intent = new Intent(this,SecondExample.class);
        startActivity(intent);
    }
}
