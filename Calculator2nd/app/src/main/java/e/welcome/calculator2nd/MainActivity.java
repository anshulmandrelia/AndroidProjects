package e.welcome.calculator2nd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView t;
    float firstvalue,secondvalue;
    boolean add,sub,div,mul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Button seven,eight,nine,plus,four,five,six,one,
                two,minus,three,multiply,equals,divide,zero,point,c;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t=findViewById(R.id.field);
        seven=findViewById(R.id.press7);
        eight=findViewById(R.id.press8);
        nine=findViewById(R.id.press9);
        plus=findViewById(R.id.pressplus);
        four=findViewById(R.id.press4);
        five=findViewById(R.id.press5);
        six=findViewById(R.id.press6);
        one=findViewById(R.id.press1);
        two=findViewById(R.id.press2);
        minus=findViewById(R.id.pressminus);
        three=findViewById(R.id.press3);
        multiply=findViewById(R.id.pressmultiply);
        equals=findViewById(R.id.equals);
        divide=findViewById(R.id.pressdivide);
        zero=findViewById(R.id.press0);
        point=findViewById(R.id.point);
        c=findViewById(R.id.preesc);
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t.setText(t.getText()+"7");
            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t.setText(t.getText()+"4");
            }
        });
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t.setText(t.getText()+"1");
            }
        });
        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t.setText(t.getText()+"0");
            }
        });
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t.setText(t.getText()+"8");
            }
        });
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t.setText(t.getText()+"5");
            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t.setText(t.getText()+"2");
            }
        });
        point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s=t.getText().toString();
                 int y=s.length();
                char g[]=new char[y];
                g=s.toCharArray();
                int count=0;
                for(int i=0;i<y;i++)
                {
                if(g[i]=='.')
                {
                    count++;
                }
                else
                {
                   count=count;
            }
                }
                if(count==0)
                {
                    t.setText(t.getText()+".");
                }
                else
                {
                    Toast.makeText(MainActivity.this, ". already exist", Toast.LENGTH_SHORT).show();
                    t.setText(t.getText());
                 }
            }
        });
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t.setText(t.getText()+"9");
            }
        });
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t.setText(t.getText()+"6");
            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t.setText(t.getText()+"3");
            }
        });
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(t.getText().length()==0)
                {
                    Toast.makeText(MainActivity.this, "First enter the number", Toast.LENGTH_SHORT).show();
                    t.setText("");
                }
                else
                {
                    firstvalue=Float.parseFloat(t.getText() + "");
                    add=true;
                    t.setText("");
                }}
        });


        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(t.getText().length()==0)
                {
                    Toast.makeText(MainActivity.this, "First enter the number", Toast.LENGTH_SHORT).show();
                    t.setText("");
                }
                else
                {
                    firstvalue=Float.parseFloat(t.getText() + "");
                    sub=true;
                    t.setText("");
                }
            }
        });
        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(t.getText().length()==0)
                {
                    Toast.makeText(MainActivity.this, "First enter the number", Toast.LENGTH_SHORT).show();
                    t.setText("");
                }
                else
                {
                    firstvalue=Float.parseFloat(t.getText() + "");
                    mul=true;
                    t.setText("");
                }
            }
        });
        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(t.getText().length()==0)
                {
                    Toast.makeText(MainActivity.this, "First enter the number", Toast.LENGTH_SHORT).show();
                    t.setText("");
                }
                else
                {
                    firstvalue=Float.parseFloat(t.getText() + "");
                    div=true;
                    t.setText("");
                }
            }
        });
        equals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(t.getText().length()==0)
                {
                    Toast.makeText(MainActivity.this, "First enter the number", Toast.LENGTH_SHORT).show();
                    t.setText("");
                }
                else
                {
                secondvalue = Float.parseFloat(t.getText() + "");
                if (add==true) {
                    t.setText(firstvalue + secondvalue + "");
                    add = false;
                }
                if (sub == true) {
                    t.setText(firstvalue - secondvalue + "");
                    sub = false;
                }
                if (mul == true) {
                    t.setText(firstvalue * secondvalue + "");
                    mul = false;
                }

                if (div == true) {
                    t.setText(firstvalue / secondvalue + "");
                    div = false;
                }
            }}
        });
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t.setText("");
            }
        });
    }
}
