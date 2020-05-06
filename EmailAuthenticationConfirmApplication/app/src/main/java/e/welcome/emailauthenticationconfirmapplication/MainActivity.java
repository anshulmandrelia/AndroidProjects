package e.welcome.emailauthenticationconfirmapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    EditText e1,e2;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1=findViewById(R.id.email);
        b=findViewById(R.id.signup);
        b.setOnClickListener(new View.OnClickListener() {

            ActionCodeSettings actionCodeSettings =
                    ActionCodeSettings.newBuilder()
                            // URL you want to redirect back to. The domain (www.example.com) for this
                            // URL must be whitelisted in the Firebase Console.
                            .setUrl("https://emailauthenticationconfirmappl." +
                                    "firebaseapp.com/__/auth/action?mode=<action>&oobCode=<code>")
                            // This must be true
                            .setHandleCodeInApp(true)
                            .setAndroidPackageName(
                                    "e.welcome.emailauthenticationconfirmapplication",
                                    true, /* installIfNotAvailable */
                                    "23"    /* minimumVersion */)
                            .build();
            @Override
            public void onClick(View view) {
                String email=e1.getText().toString();
                FirebaseAuth auth = FirebaseAuth.getInstance();
                aut                auth.sendSignInLinkToEmail(email,actionCodeSettings)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(MainActivity.this,
                                            "email sent", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                }
        });

    }
}
