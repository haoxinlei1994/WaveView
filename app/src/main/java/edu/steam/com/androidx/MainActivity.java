package edu.steam.com.androidx;
import android.app.Activity;
import android.os.Bundle;
import org.greenrobot.eventbus.Subscribe;


public class MainActivity extends Activity {

    @Subscribe
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

}