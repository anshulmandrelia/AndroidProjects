package e.welcome.myflashlightlco;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
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
    Button bt_disco;
    ImageView img;
    boolean flashlightstatus=false;
     private final int camera_request=50;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt_enable=findViewById(R.id.enable_bt);
        img=findViewById(R.id.img);
        bt_disco=findViewById(R.id.disco);
        final boolean hascameraflash=getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
        boolean isenable= ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                ==PackageManager.PERMISSION_GRANTED;
        bt_enable.setEnabled(!isenable);
        bt_disco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                blinkflash();
            }
        });
        bt_enable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions
                        (MainActivity.this,new String[]{Manifest.permission
                .CAMERA},camera_request);
                img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(hascameraflash)
                        {
                            if(flashlightstatus)
                            {
                                turnoflight();
                            }
                            else
                            {
                                turnonlight();
                            }
                        }
                        else {
                            Toast.makeText(MainActivity.this,"YOUR CAMERA HAS" +
                                    "NOT FLASH",Toast.LENGTH_SHORT).show();

                        }
                    }
                });


            }
        });
    }

    private void turnonlight() {
        CameraManager cm=(CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try
        {
            String id=cm.getCameraIdList()[0];
            cm.setTorchMode(id,true);
            flashlightstatus=true;
            img.setImageResource(R.drawable.button_on);
        }
        catch (Exception e)
        {}
    }
    private void turnoflight() {
        CameraManager cm=(CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try
        {
            String id=cm.getCameraIdList()[0];
            cm.setTorchMode(id,false);
            flashlightstatus=false;
            img.setImageResource(R.drawable.button_off);
        }
        catch (Exception e)
        {

        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults)
    {
        switch(requestCode){
            case camera_request:
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
    public void blinkflash()
    {
        CameraManager cameraManager=(CameraManager) getSystemService(Context.CAMERA_SERVICE);
        String p="01010101010101010101010101010101010101010010101010";
        long delay=500;
        for(int i=0;i<p.length();i++)
        {  if(p.charAt(i) == '0')
        {   try
        {
            String id=cameraManager.getCameraIdList()[0];
            cameraManager.setTorchMode(id,false);
        }
        catch (Exception e)
        {
        }
        }
        else
        {
            try
            {
                String id=cameraManager.getCameraIdList()[0];
                cameraManager.setTorchMode(id,true);
            }
            catch (Exception e)
            {}
        }
        try
        {
            Thread.sleep(delay);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        }
    }
}
