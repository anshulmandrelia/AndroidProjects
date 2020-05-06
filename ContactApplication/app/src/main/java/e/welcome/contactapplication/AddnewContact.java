package e.welcome.contactapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddnewContact extends AppCompatActivity {
    SQLiteDatabase sqlitedb;
    private String cname,cemail,cnumber,cimage;
    private Uri ImageUri;
    private ProgressDialog loadingBar1;
    private android.widget.ImageView inputcontactImage;
    private Button AddNewcontactButton;
    private EditText inputContactName,InputContactEmail,InputContactNumber;
    private static final int GalleryPick=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnew_contact);
        inputcontactImage=findViewById(R.id.select_Event_image111);
        AddNewcontactButton=findViewById(R.id.add_Event);
        inputContactName=findViewById(R.id.contact_name);
        InputContactEmail=findViewById(R.id.contact_email);
        InputContactNumber=findViewById(R.id.contacts_number);
        sqlitedb=openOrCreateDatabase("ContactDB", Context.MODE_PRIVATE,null);
        sqlitedb.execSQL("CREATE TABLE IF NOT EXISTS ContactDB(EmpId INTEGER PRIMARY KEY AUTOINCREMENT," +
           "EmpName VARCHAR(255),EmpMail VARCHAR(255),EmpNumber VARCHAR(255));");
        Toast.makeText(this, "Database created", Toast.LENGTH_SHORT).show();
        inputcontactImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenGallery();
            }
        });
     AddNewcontactButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
           ValidateProductData();
        }
    });
}
    private void OpenGallery() {
        Intent galleryIntent=new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, GalleryPick);
    }
  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
      super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==GalleryPick && resultCode == RESULT_OK && data != null)
        {
            ImageUri= data.getData();
            inputcontactImage.setImageURI(ImageUri);
        }
   }
    private void ValidateProductData()
    {  cemail=InputContactEmail.getText().toString();
       cname=inputContactName.getText().toString();
       cnumber=InputContactNumber.getText().toString();
      if(TextUtils.isEmpty(cemail))
       {
          Toast.makeText(this, "Please Write Event Description", Toast.LENGTH_SHORT).show();
       }
       else if(TextUtils.isEmpty(cname))
       {
           Toast.makeText(this, "Please Write contact name", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(cnumber))
        {
           Toast.makeText(this, "Please Write contact number", Toast.LENGTH_SHORT).show();
        }
        else
        {
            StoreProductInfomation();

        }}
    private void StoreProductInfomation() {
        loadingBar1.setTitle("Adding new Contact");
        loadingBar1.setMessage("Please Wait  while we are checking the credentials");
        loadingBar1.setCanceledOnTouchOutside(false);
        loadingBar1.show();
        sqlitedb.execSQL("INSERT INTO ContactDB(EmpName,EmpMail,EmpAge)VALUES('"+cname+"','"+cemail+
                "','"+cnumber+"');");
        Toast.makeText(this, "Record saved", Toast.LENGTH_SHORT).show();
    }
}
