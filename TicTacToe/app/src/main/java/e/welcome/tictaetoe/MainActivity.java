package e.welcome.tictaetoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    int i,j;
    Boolean player_1chance=true;
    Button b1[][] =new Button[3][3];
    String[][] f=new String[3][3];
    Button bt_reset;
    TextView tv;
    int roundcount=0;
    LinearLayout l;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.text_view);
        l=findViewById(R.id.mylayout);
        for (i = 0; i < 3; i++) {
            for (j = 0; j < 3; j++) {
                String btnid = "button_" + i + j;
                int resId = getResources().getIdentifier(btnid, "id", getPackageName());
                b1[i][j] = findViewById(resId);
                b1[i][j].setOnClickListener(this);
            }}
                bt_reset=findViewById(R.id.button_reset);
                bt_reset.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        reset_game();
                    }
                });
    }
    private void checkforwin()
    {   for(i=0;i<3;i++)
    {
        for(j=0;j<3;j++)
        {
            f[i][j]=b1[i][j].getText().toString();
        }
    }
        for(i=0;i<3;i++)
        {
            if(f[i][0]==f[i][1]&&f[i][0]==f[i][2]&&!f[i][0].equals(""))
            {
                Toast.makeText(this, "wins", Toast.LENGTH_SHORT).show();
                buttondiabled();
                whowins();
            }
        }
        for(i=0;i<3;i++)
        {
            if(f[0][i]==f[1][i]&&f[0][i]==f[2][i]&&!f[0][i].equals(""))
            {
                Toast.makeText(this, "wins", Toast.LENGTH_SHORT).show();
                buttondiabled();
                whowins();
            }
        }
        if(f[0][0]==f[1][1]&&f[0][0]==f[2][2]&&!f[0][0].equals(""))
        {

            Toast.makeText(this, "wins", Toast.LENGTH_SHORT).show();
            buttondiabled();
            whowins();

        }
        if(f[0][2]==f[1][1]&&f[0][2]==f[2][0]&&!f[0][2].equals(""))
        {

            Toast.makeText(this, "wins", Toast.LENGTH_SHORT).show();
            buttondiabled();
            whowins();
        }
        if(roundcount==9)
        {   Toast.makeText(this, "Draw", Toast.LENGTH_SHORT).show();
           tv.setText("Draw");
            buttondiabled();
        }
    }
    private void reset_game() {
        player_1chance=true;
        for(i=0;i<3;i++)
        {
            for(j=0;j<3;j++)
            {
                b1[i][j].setEnabled(true);
                b1[i][j].setText("");
                tv.setText("");
                roundcount=0;
            }
        }
    }

    @Override
    public void onClick(View view) {
        if(player_1chance)
        {
            ((Button) view).setText("X");
            player_1chance=false;
            ((Button) view).setEnabled(false);
        }
        else
        {
            ((Button) view).setText("0");
            player_1chance=true;
            ((Button) view).setEnabled(false);
        }
        roundcount++;
        checkforwin();
    }
    public void buttondiabled()
    {
        for(i=0;i<3;i++)
        {
            for(j=0;j<3;j++)
            {
                b1[i][j].setEnabled(false);
            }
        }}
    public void whowins()
    { if(player_1chance==true)
    {
        tv.setText("Player two wins");
        roundcount=0;
    }
   else
    {
        tv.setText("Player one wins");
        roundcount=0;
    }
    }
}