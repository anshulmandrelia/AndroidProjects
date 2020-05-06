package e.welcome.scientificcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View overlay=findViewById(R.id.mylayout);
        overlay.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        View overlay1=findViewById(R.id.mylayout1);
        overlay1.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);


    }
}
