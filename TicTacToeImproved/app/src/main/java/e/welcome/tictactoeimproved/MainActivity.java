package e.welcome.tictactoeimproved;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private Button button;
    int first=0,last,count=0;
    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
         t=findViewById(R.id.textView5);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView =findViewById(R.id.image_toe);
        float pivotX=imageView.getHeight()/2;
        float pivoty=imageView.getWidth()/2;
        last=360;
        Animation rotate=new RotateAnimation(first,last,155,155);
        rotate.setDuration(2000);
        imageView.startAnimation(rotate);

        {
            Intent i = new Intent(MainActivity.this, GameActivity.class);
            startActivity(i);
        }

    }

}
