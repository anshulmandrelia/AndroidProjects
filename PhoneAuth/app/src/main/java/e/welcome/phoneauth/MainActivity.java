package e.welcome.phoneauth;

import android.arch.core.executor.TaskExecutor;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import java.util.concurrent.TimeUnit;
public class MainActivity extends AppCompatActivity {
    String verificationId;
    String number;
    EditText e1,e2,e3;
    ImageView i;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1=findViewById(R.id.countrycode);
        e2=findViewById(R.id.number);
        e3=findViewById(R.id.otpverify);
        i=findViewById(R.id.sendotp);
        firebaseAuth=FirebaseAuth.getInstance();
        i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number=e2.getText().toString().trim();
                if(number.isEmpty() || number.length() <10)
                {
                 e2.setError("Valid number is required");
                 e2.requestFocus();
                 return;
                }
                final String phoneNumber="+"+"91"+number;
                sendVerificationcode(phoneNumber);
            }
        });
    }
    private void sendVerificationcode(String phoneNumber) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(phoneNumber,60, TimeUnit.SECONDS, TaskExecutors
                .MAIN_THREAD,mCallBack
                );
    }
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
    mCallBack =new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verificationId=s;
        }
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            String code=phoneAuthCredential.getSmsCode();
            if(code !=null)
            { e3.setText(code);
                verifycode(code);
            }
        }
        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    };
    public void verifycode(String code)
    {
        PhoneAuthCredential credential=PhoneAuthProvider.getCredential(verificationId,code);
        signInWithCrdentials(credential);
    }
    public void signInWithCrdentials(PhoneAuthCredential credential)
    {
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                      if(task.isSuccessful())
                      { Intent i=new Intent(MainActivity.this,verifyotp.class);
                          startActivity(i);
                      }
                      else
                      {
                          Toast.makeText(MainActivity.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                      }
                    }
                });
    }
}