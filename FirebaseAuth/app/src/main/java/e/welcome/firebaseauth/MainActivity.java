package e.welcome.firebaseauth;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.ProviderQueryResult;

public class MainActivity extends AppCompatActivity {
    EditText e1,e2;
    Button b,b1;
    String email,password;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1=findViewById(R.id.email);
        e2=findViewById(R.id.password);
        b=findViewById(R.id.signup);
        b1=findViewById(R.id.LoginPage);
        firebaseAuth=FirebaseAuth.getInstance();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,Login.class);
                startActivity(i);
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 if(e1.getText().toString().length()==0)
                 {
                     Toast.makeText(MainActivity.this, "Plese enter a email",
                             Toast.LENGTH_SHORT).show();
                 }
                 else
                 {
                if(e2.getText().length()<5)
                {
                    Toast.makeText(MainActivity.this,"MINIMUM PASSWORD LENGTH MUST BE "
                            +
                            "5",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    email=e1.getText().toString();
                   password=e2.getText().toString();
                   firebaseAuth.fetchProvidersForEmail(email).addOnCompleteListener(new OnCompleteListener<ProviderQueryResult>() {
                       @Override
                       public void onComplete(@NonNull Task<ProviderQueryResult> task) {
                      boolean check=!task.getResult().getProviders().isEmpty();
                      if(!check)
                      {
                          signup();
                      }
                      else
                      {
                          Toast.makeText(MainActivity.this, "Email already exist", Toast.LENGTH_SHORT).show();
                      }
                       }
                   });


                }
                 }

            }
        });
    }

    private void signup() {
        firebaseAuth.createUserWithEmailAndPassword(email,
                password).addOnCompleteListener(this,
                new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(MainActivity.this, "Sign up succesfullly", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Toast.makeText(MainActivity.this, "Not Registered", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
