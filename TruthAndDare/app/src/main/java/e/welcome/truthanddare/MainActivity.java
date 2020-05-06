package e.welcome.truthanddare;

import android.graphics.drawable.Animatable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private Button button;
    int first=0,last,count=0;
     Random r;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView =findViewById(R.id.imageView3);
        button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spin();
                count++;
                if(count>0)
                {
                    first=last;
                }
            }
        });
    }
    private void spin() {
        float pivotX=imageView.getHeight()/2;
        float pivoty=imageView.getWidth()/2;
        r=new Random();
        int a=r.nextInt(3600);
        last=a;
        Animation rotate=new RotateAnimation(first,last,pivotX,pivoty);
        rotate.setDuration(2000);
        rotate.setFillAfter(true);
        imageView.startAnimation(rotate);
    }
}
