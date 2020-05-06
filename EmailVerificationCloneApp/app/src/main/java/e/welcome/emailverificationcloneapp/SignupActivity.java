package e.welcome.emailverificationcloneapp;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private DatabaseReference db = FirebaseDatabase.getInstance().getReference("");
    EditText e1,e2,e3;
    Button b;
    String regex = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$";
    String regexpass = "^(?=.*[A-Z].*[A-Z])(?=.*[!@#$&*])(?=.*[0-9].*[0-9])(?=.*[a-z].*[a-z].*[a-z]).{8}$";
    String name,email,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mAuth = FirebaseAuth.getInstance();
        e1=findViewById(R.id.name);
        e2=findViewById(R.id.email);
        e3=findViewById(R.id.password);
        b=findViewById(R.id.signup);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreatenewAccount();
            }
        });

    }

    private void CreatenewAccount() {
        verifyDetails();
        if (!verifyDetails()) {
            return;
        }
        final ProgressDialog p = new ProgressDialog(this);
        p.setMessage("Signing Up...");
        p.show();
        p.setCancelable(false);
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            p.cancel();
                            sendVerificationLink();
                        } else {
                            p.cancel();
                            Toast.makeText(SignupActivity.this,task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            Toast.makeText(SignupActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    private void sendVerificationLink() {
        mUser = mAuth.getCurrentUser();
        mUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                AlertDialog.Builder alert = new AlertDialog.Builder(SignupActivity.this);
                alert.setTitle("Verify your email");
                alert.setMessage("Go to your email to verify your account");
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(SignupActivity.this,MainActivity.class));
                        finish();
                    }
                });
                AlertDialog newalert = alert.create();
                newalert.show();
            }
        });
    }

    private boolean verifyDetails() {
        name = e1.getText().toString();
        email = e2.getText().toString();
        password = e3.getText().toString();
        if(name.equals("") || email.equals("") || password.equals("")){
            Toast.makeText(this,"All fields are compulsory",Toast.LENGTH_LONG).show();
            return false;
        }
        if(!(password.length()>=8)){
            //give user notice
            e3.setError("Min 8 characters required");
            return false;
        }
        if(!email.matches(regex)){
            //give user notice
            e2.setError("Please enter a valid email address");
            return false;
        }
        return true;
    }
    void emptyalllfields(){
        e1.setText("");
        e2.setText("");
        e3.setText("");
    }
}
