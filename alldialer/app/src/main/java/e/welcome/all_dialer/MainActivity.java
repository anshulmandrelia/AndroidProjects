package e.welcome.all_dialer;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText e;
    Button b1,b2,b3;
    private static final int request=40;
    private static final int request1=41;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(MainActivity.this,new String[]
                {Manifest.permission.CALL_PHONE},request);
        ActivityCompat.requestPermissions(MainActivity.this,new String[]
                {Manifest.permission.SEND_SMS},request1);
        e=findViewById(R.id.number);
        b1=findViewById(R.id.wtsup);
        b2=findViewById(R.id.msg);
        b3=findViewById(R.id.call);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String num=e.getText().toString();
                Intent i=new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:"+num));
                if(ActivityCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.CALL_PHONE)!=(PackageManager.PERMISSION_GRANTED))
                {
                    ActivityCompat.requestPermissions(MainActivity.this,new String[]
                            {Manifest.permission.CALL_PHONE},request);
                }
                else
                {

                    startActivity(i);
                }
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String num=e.getText().toString();
                Uri uri=Uri.parse("smsto:"+num);
                Intent sendintent=new Intent(Intent.ACTION_SENDTO,uri);
                sendintent.putExtra(Intent.EXTRA_TEXT,"jhioho");
                sendintent.setPackage("com.whatsapp");
                startActivity(sendintent);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String num=e.getText().toString();
                Uri uri=Uri.parse("smsto:"+num);
                Intent i=new Intent(Intent.ACTION_SENDTO,uri);
                i.putExtra(Intent.EXTRA_TEXT,"jhioho");

                if(ActivityCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.SEND_SMS)!=(PackageManager.PERMISSION_GRANTED))
                {
                    ActivityCompat.requestPermissions(MainActivity.this,new String[]
                            {Manifest.permission.SEND_SMS},request1);
                }
                else
                { startActivity(i);
                }

            }
        });
    }
}
