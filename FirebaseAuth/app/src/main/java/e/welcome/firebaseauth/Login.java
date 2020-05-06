package e.welcome.firebaseauth;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Process;
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

public class Login extends AppCompatActivity {
    EditText e1,e2;
    Button b,b1,b2;
    private FirebaseAuth firebaseAuth;
   String email,password;
   ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        e1=findViewById(R.id.emailsignin);
        e2=findViewById(R.id.passwordsignin);
        b=findViewById(R.id.signin);
        b1=findViewById(R.id.forgotpassword);
        b2=findViewById(R.id.signuplogin);
        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);
        if(firebaseAuth.getCurrentUser()!=null)
        {
            finish();
            startActivity(new Intent(getApplicationContext(),HomeActivity.class));
        }
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(e1.getText().toString().length()==0)
                {
                    Toast.makeText(Login.this, "Plese enter a email", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    if(e2.getText().length()<5)
                    {
                        Toast.makeText(Login.this,"MINIMUM PASSWORD LENGTH MUST BE " +
                                "5",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {   email=e1.getText().toString();
                        password=e2.getText().toString();
                        signin();

                    }
                }
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           Intent i=new Intent(Login.this,ForgotPassword.class);
           startActivity(i);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Login.this,MainActivity.class);
                startActivity(i);
            }
        });
    }

    private void signin() {
        progressDialog.setMessage("LOGIN USER........");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {   progressDialog.cancel();
                            Toast.makeText(Login.this, "LOGIN SUCCESFULLY",
                                    Toast.LENGTH_SHORT).show();

                            Intent i=new Intent(Login.this,HomeActivity.class);
                            startActivity(i);
                            finish();
                        }
                        else
                        {   progressDialog.cancel();
                            Toast.makeText(Login.this, "Email or password incorrect",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });

    }
}
