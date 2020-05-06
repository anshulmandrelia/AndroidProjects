package e.welcome.emailverificationcloneapp;

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
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {
    EditText e;
    Button b;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        firebaseAuth=FirebaseAuth.getInstance();
        b=findViewById(R.id.btn_sendcode);
        e=findViewById(R.id.forgotemail);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str=e.getText().toString();
                firebaseAuth.sendPasswordResetEmail(str).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(ForgotPassword.this, "Reset code has been sent to your registered email", Toast.LENGTH_SHORT).show();
                            Intent i=new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(i);
                        }
                        else
                        {
                            Toast.makeText(ForgotPassword.this, "Wrong email or any error occured", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
