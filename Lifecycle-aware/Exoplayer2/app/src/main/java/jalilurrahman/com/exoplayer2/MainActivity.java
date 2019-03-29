package jalilurrahman.com.exoplayer2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PlayerView playerView = findViewById(R.id.player_view);
        String videoUrl = "http://docs.evostream.com/sample_content/assets/bunny.mp4";

        getLifecycle().addObserver(new VideoPlayerComponent(this,playerView,videoUrl));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
