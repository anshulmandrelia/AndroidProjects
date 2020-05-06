package e.welcome.datingappdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Main2Activity extends AppCompatActivity {
    String email,password,name,number,gender;
    EditText et_name,et_number,et_gender;
    Button bt_signin;
 private    DatabaseReference myRef73;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
       // FirebaseDatabase database = FirebaseDatabase.getInstance();

        et_name=findViewById(R.id.name);
        et_number=findViewById(R.id.number);
        et_gender=findViewById(R.id.gender);
        bt_signin=findViewById(R.id.signin);

        myRef73  = FirebaseDatabase.getInstance().getReference();

        bt_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email=getIntent().getStringExtra("email");
                password=getIntent().getStringExtra("password");
                 name=et_name.getText().toString();
                 number=et_number.getText().toString();
                 gender=et_gender.getText().toString();
                 User u=new User(name,number,gender,email,password);
               myRef73.child("users").child(name).setValue(u);

            }
        });
    }
}
