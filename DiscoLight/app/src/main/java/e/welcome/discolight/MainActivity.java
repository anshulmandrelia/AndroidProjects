package e.welcome.discolight;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    SeekBar seek;
    Switch start;
    int blinkInterval;
    boolean ison=false;
    boolean btenabled;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start=findViewById(R.id.btn);
        seek=findViewById(R.id.seek);
        seek.setMax(10);

        start.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission
                        .CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                            Manifest.permission.CAMERA}, 10);
                } else if (start.isChecked()) {
                    startblink();
                }
            }
            });

                }
    private void startblink() {
        Timer t1=new Timer();
        if(!start.isChecked())
        {
            t1.cancel();
            flashlightoff();
            ison=false;
            return;
        }
        blinkInterval = 1000/(seek.getProgress() == 0?1 : seek.getProgress());

        t1.schedule(new TimerTask() {
            @Override
            public void run() {
                if(ison)
                {flashlightoff();
                ison=false;
                }
                else
                {
                  flashlighton();
                  ison=true;
                }
              startblink();
            }
            },blinkInterval);
        }

    private void flashlighton() {
        CameraManager cameraManager=(CameraManager)getSystemService(Context.CAMERA_SERVICE);
        try
        {  String cameraId= cameraManager.getCameraIdList()[0];
          cameraManager.setTorchMode(cameraId,true);
        }
        catch(Exception e)
        {
        }
    }

    private void flashlightoff() {
        CameraManager cameraManager=(CameraManager)getSystemService(Context.CAMERA_SERVICE);
        try
        {  String cameraId= cameraManager.getCameraIdList()[0];
            cameraManager.setTorchMode(cameraId,false);
        }
        catch(Exception e)
        {
        }
    }
    }
