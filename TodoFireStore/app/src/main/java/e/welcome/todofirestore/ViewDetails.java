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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ViewDetails extends AppCompatActivity {
    EditText title,description;
    String id,titleedit,descriptionedit;
    private DatabaseReference dbms;
    Button update,delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);
        title=findViewById(R.id.title_view);
        description=findViewById(R.id.description_view);
        update=findViewById(R.id.updateButton);
        delete=findViewById(R.id.deleteButton);
        id=getIntent().getStringExtra("id");
        titleedit=getIntent().getStringExtra("title");
        descriptionedit=getIntent().getStringExtra("descr");
        title.setText(titleedit);
        description.setText(descriptionedit);
        dbms= FirebaseDatabase.getInstance().getReference();
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ViewDetails.this, id, Toast.LENGTH_SHORT).show();
                updatenotes(id);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ViewDetails.this, id, Toast.LENGTH_SHORT).show();
                deletenotes(id);
            }
        });
    }
    private void deletenotes(String id) {
        dbms.child("Notes").child(id).removeValue()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(ViewDetails.this, "Note deleted", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    }
                });
    }
    private void updatenotes(String id) {
        String titlesend,descriptionsend;
        titlesend=title.getText().toString();
        descriptionsend=description.getText().toString();
        ListData listdata=new ListData(titlesend,descriptionsend);
        dbms.child("Notes").child(id).setValue(listdata).
                addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(ViewDetails.this,"Notes updated", Toast.LENGTH_SHORT).show();
                     startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    }
                });
    }
}
