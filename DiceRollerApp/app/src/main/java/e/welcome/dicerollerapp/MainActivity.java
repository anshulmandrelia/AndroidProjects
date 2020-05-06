package e.welcome.dicerollerapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    private Random myrandomnumber=new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        imageView=findViewById(R.id.imageView111);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rollourDice();
            }
        });
    }

    private void rollourDice() {
        int myrannumber=myrandomnumber.nextInt(6)+1;
        switch (myrannumber)
        {
            case 1:  imageView.setImageResource(R.drawable.dice1);
                break;
            case 2:  imageView.setImageResource(R.drawable.dice2);
                break;
            case 3:   imageView.setImageResource(R.drawable.dice3);
                break;
            case 4:    imageView.setImageResource(R.drawable.dice4);
                break;
            case 5:   imageView.setImageResource(R.drawable.dice5);
                break;
            case 6:   imageView.setImageResource(R.drawable.dice6);
                break;
        }
    }
}
