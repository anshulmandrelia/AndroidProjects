package e.welcome.myflashlightapp;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button bt_enable;
    ImageView img;
    private static final int CAMERA_REQUEST=50;
    private boolean flashlightstatus=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt_enable=findViewById(R.id.bt_enable);
        img=findViewById(R.id.Img_Light);
        final boolean hascameraflash=getPackageManager().
        hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
        boolean isenable= ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                ==PackageManager.PERMISSION_GRANTED;
        bt_enable.setEnabled(!isenable);
        bt_enable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(MainActivity.this,new String[]
                        {Manifest.permission.CAMERA},CAMERA_REQUEST);
            }
        });

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(hascameraflash)
                {
                    if(flashlightstatus)
                    {
                        flashLightOff();
                    }
                    else
                    {
                        flashLightOn();
                    }
                }
                else
                {
                    Toast.makeText(MainActivity.this,
                            "NO FLASH AVAILABLE IN YOUR PHONE",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void flashLightOn() {
        CameraManager cameraManager=(CameraManager)getSystemService(Context.CAMERA_SERVICE);
        try {
            String cameraId=cameraManager.getCameraIdList()[0];
            cameraManager.setTorchMode(cameraId,true);
            flashlightstatus=true;
            img.setImageResource(R.drawable.button_on);
        } catch (CameraAccessException e) {

        }
    }
    private void flashLightOff() {
        CameraManager cameraManager=(CameraManager)getSystemService(Context.CAMERA_SERVICE);
        try {
            String cameraId=cameraManager.getCameraIdList()[0];
            cameraManager.setTorchMode(cameraId,false);
            flashlightstatus=false;
            img.setImageResource(R.drawable.button_off);
        } catch (CameraAccessException e) {

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults)
    {
        switch(requestCode){
            case CAMERA_REQUEST:
                if(grantResults.length > 0&& grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED)
                {  bt_enable.setEnabled(false);
                bt_enable.setText("Camera Enabled");
                img.setEnabled(true);
                }
                else
                { Toast.makeText(MainActivity.this,"Permission denied" +
                        " for camera",Toast.LENGTH_SHORT).
                        show();
                img.setEnabled(false);
                }
                break;


        }
    }
}
