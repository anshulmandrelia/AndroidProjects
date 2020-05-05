package e.welcome.drumpadlco;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.security.PrivateKey;

public class MainActivity extends AppCompatActivity {
    Button b1,b2,b3,b4,b5,b6,b7,b8;
     private SoundPool soundpool;
     private int sound1,sound2,sound3,sound4,sound5,sound6,sound7,sound8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View overlay=findViewById(R.id.mylayout);
        overlay.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN
        |View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes audioAttributes =new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();
            soundpool = new SoundPool.Builder()
                    .setMaxStreams(8)
                    .setAudioAttributes(audioAttributes)
                    .build();
        }
        else
        {
            soundpool=new SoundPool(8, AudioManager.STREAM_MUSIC,0);

        }
        sound1=soundpool.load(this,R.raw.one,1);
        sound2=soundpool.load(this,R.raw.two,1);
        sound3=soundpool.load(this,R.raw.three,1);
        sound4=soundpool.load(this,R.raw.four,1);
        sound5=soundpool.load(this,R.raw.fv,1);
        sound6=soundpool.load(this,R.raw.sixth,1);
        sound7=soundpool.load(this,R.raw.seventh,1);
        sound8=soundpool.load(this,R.raw.eighth,1);


       b1=findViewById(R.id.button1);
       b2=findViewById(R.id.button2);
       b3=findViewById(R.id.button3);
       b4=findViewById(R.id.button4);
       b5=findViewById(R.id.button5);
       b6=findViewById(R.id.button6);
       b7=findViewById(R.id.button7);
       b8=findViewById(R.id.button8);


       b1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               soundpool.play(sound1,1,1,0,0,1);
           }
       });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundpool.play(sound2,1,1,0,0,1);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundpool.play(sound3,1,1,0,0,1);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundpool.play(sound4,1,1,0,0,1);
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundpool.play(sound5,1,1,0,0,1);
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundpool.play(sound6,1,1,0,0,1);
            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundpool.play(sound7,1,1,0,0,1);
            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundpool.play(sound8,1,1,0,0,1);
            }
        });}
        @Override
       protected void onDestroy()
        {
             super.onDestroy();
             soundpool.release();
             soundpool=null;
        }

}
