package e.welcome.runtimepermission;

import android.Manifest;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button bt_enable,bt_enable_all;
    private static final int Asif_REQUEST=50;
    private static final int INTERNET_REQUEST=51;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt_enable=findViewById(R.id.enable);
        bt_enable_all=findViewById(R.id.enableall);
        bt_enable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ActivityCompat.requestPermissions(MainActivity.
                        this,new String[]{Manifest
                        .permission.SEND_SMS},INTERNET_REQUEST);

            }
        });
        bt_enable_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(MainActivity.
                        this,new String[]
                        {Manifest.permission.CAMERA,Manifest.permission.
                                ACCESS_COARSE_LOCATION,
                                Manifest.permission.READ_EXTERNAL_STORAGE}
                                ,Asif_REQUEST);
            }});

    }
}
