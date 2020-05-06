package e.welcome.emailverificationcloneapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    Button b1;
    EditText eemail,epassword;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    String regex = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$";
    TextView tsignup,tforgotpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        tsignup=findViewById(R.id.txt);
        tforgotpassword=findViewById(R.id.txtpassword);
        b1=findViewById(R.id.login);
        eemail=findViewById(R.id.emaillogin);
        epassword=findViewById(R.id.emailpassword);
        if(mAuth.getCurrentUser()!=null)
        {
            finish();
            startActivity(new Intent(getApplicationContext(),Main2Activity.class));
        }
        tsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),SignupActivity.class);
                startActivity(i);
            }
        });
        tforgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),ForgotPassword.class);
                startActivity(i);
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=eemail.getText().toString();
                if(eemail.getText().toString().isEmpty())
                {
                    eemail.setError("Write your email");
                }
                if(!email.matches(regex)){
                    //give user notice
                    eemail.setError("Please enter a valid email address");
                }
                else
                {    String password=epassword.getText().toString();

                    mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {   mUser=mAuth.getCurrentUser();
                                if(mUser.isEmailVerified())
                                {
                                    Intent i=new Intent(getApplicationContext(),Main2Activity.class);
                                    startActivity(i);
                                }
                                else
                                {
                                    Toast.makeText(MainActivity.this, "You email are not verified yet", Toast.LENGTH_SHORT).show();
                                    mAuth.signOut();
                                }
                            }
                            else
                            {
                                Toast.makeText(MainActivity.this,task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            }
        });

    }
}
