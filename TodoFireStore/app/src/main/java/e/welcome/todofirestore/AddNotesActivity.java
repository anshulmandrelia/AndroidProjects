package e.welcome.todofirestore;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddNotesActivity extends AppCompatActivity {
    Button b;
    EditText e1,e2;
    String title,description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);
        e1=findViewById(R.id.description);
        e2=findViewById(R.id.title1);
        b=findViewById(R.id.addButton);
        final FirebaseFirestore database = FirebaseFirestore.getInstance();
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title=e2.getText().toString();
                description=e1.getText().toString();
                CollectionReference collection=database.collection("Notebook");
                ListData item=new ListData(title,description);
                collection.add(item)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(AddNotesActivity.this, "Notes Added", Toast.LENGTH_SHORT).show();
                                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(i);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddNotesActivity.this, "Not Added", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }
}
