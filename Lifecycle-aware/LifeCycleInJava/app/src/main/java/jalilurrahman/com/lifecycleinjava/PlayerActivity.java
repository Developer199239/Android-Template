package jalilurrahman.com.lifecycleinjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.Lifecycle;

public class PlayerActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        getLifecycle().addObserver(new MusicPlayer(this,getLifecycle()));
    }
}
