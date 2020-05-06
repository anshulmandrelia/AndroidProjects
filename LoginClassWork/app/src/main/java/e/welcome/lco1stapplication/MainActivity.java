package e.welcome.lco1stapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button loginbtn;
    EditText email;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginbtn=findViewById(R.id.login);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailid=email.getText().toString();
                String password1=password.getText().toString();
                if(emailid.equals("Anshul")&&password1.equals("Anshul"))
                {
                    Toast.makeText(MainActivity.this, "Logged In", Toast.LENGTH_SHORT).show();}
            else
                {
                    Toast.makeText(MainActivity.this, "Wrong id or password", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}
