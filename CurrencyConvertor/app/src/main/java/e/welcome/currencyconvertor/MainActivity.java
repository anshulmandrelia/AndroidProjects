package e.welcome.currencyconvertor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {
       Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b0;
       EditText e1;
       TextView t1,t2;
       float f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b0=findViewById(R.id.clr);
        e1=findViewById(R.id.input);
        t1=findViewById(R.id.currency);
        t2=findViewById(R.id.output);
        b1=findViewById(R.id.Dollar);
        b2=findViewById(R.id.Aed);
        b3=findViewById(R.id.All);
        b4=findViewById(R.id.Amd);
        b5=findViewById(R.id.Aoa);
        b6=findViewById(R.id.Ars);
        b7=findViewById(R.id.Aud);
        b8=findViewById(R.id.Awg);
        b9=findViewById(R.id.Azn);
        b10=findViewById(R.id.Bam);
        b11=findViewById(R.id.Bdt);
        b0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                e1.setText("");
                t2.setText("");
                t1.setText("");
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    f = Float.parseFloat(e1.getText() + "");
                    t1.setText("Dollar");
                    t2.setText(f/69.65 + "");

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                f=Float.parseFloat(e1.getText()+"");
                t1.setText("Dirham");
                t2.setText(f/18.96+"");

            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                f=Float.parseFloat(e1.getText()+"");
                t1.setText("Lek");
                t2.setText(f/.65+"");

            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                f=Float.parseFloat(e1.getText()+"");
                t1.setText("Amd");
                t2.setText(f*6.90+"");

            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                f=Float.parseFloat(e1.getText()+"");
                t1.setText("KWANZA");
                t2.setText(f*5+"");

            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                f=Float.parseFloat(e1.getText()+"");
                t1.setText("Piso");
                t2.setText(f/1.55+"");

            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                f=Float.parseFloat(e1.getText()+"");
                t1.setText("Australian Dollar");
                t2.setText(f/48.31+"");

            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                f=Float.parseFloat(e1.getText()+"");
                t1.setText("Florin");
                t2.setText(f/38.51+"");

            }
        });

        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                f=Float.parseFloat(e1.getText()+"");
                t1.setText("mannat");
                t2.setText(f/40.73+"");

            }
        });

        b10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                f=Float.parseFloat(e1.getText()+"");
                t1.setText("marka");
                t2.setText(f/40.19+"");
            }
        });
        b11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                f=Float.parseFloat(e1.getText()+"");
                t1.setText("taka");
                t2.setText(f/0.82+"");
            }
        });
    }
    }
