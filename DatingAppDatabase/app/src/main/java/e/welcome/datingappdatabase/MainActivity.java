package e.welcome.datingappdatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText e1,e2;
    Button b;
    String email,password;
    ProgressBar p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        e1=findViewById(R.id.email);
        e2=findViewById(R.id.password);
        b=findViewById(R.id.signup);
        p=findViewById(R.id.progressBar);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               email=e1.getText().toString();
                password=e2.getText().toString();
                if(TextUtils.isEmpty(email))
                {
                        e1.setError("Enter the email");
                }
                else if(TextUtils.isEmpty(password))
                {
                    e2.setError("Enter password");
                }
                else
                {
                    if(password.length()<=6)
                    {
                        e2.setError("Minimum length is 6");
                    }
                    else
                    {        p.setVisibility(View.VISIBLE);
                        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful())
                                {    p.setVisibility(View.INVISIBLE);
                                    Toast.makeText(MainActivity.this, "Sign up succesfull", Toast.LENGTH_LONG).show();
                                   Intent i=new Intent(MainActivity.this,Main2Activity.class);
                                   i.putExtra("email",email);
                                   i.putExtra("password",password);
                                   startActivity(i);
                                }
                                else
                                {   p.setVisibility(View.INVISIBLE);
                                    Toast.makeText(MainActivity.this, "Signup not succesfully done", Toast.LENGTH_LONG).show();
                                    Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();

                                }
                            }
                        });
                    }
                }
            }
        });
    }
}
