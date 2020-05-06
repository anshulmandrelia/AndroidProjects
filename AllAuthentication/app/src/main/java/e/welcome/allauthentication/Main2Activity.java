package e.welcome.allauthentication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.login.widget.LoginButton;

public class Main2Activity extends AppCompatActivity {
    private LoginButton login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        login=findViewById(R.id.login_out);
    }
}
