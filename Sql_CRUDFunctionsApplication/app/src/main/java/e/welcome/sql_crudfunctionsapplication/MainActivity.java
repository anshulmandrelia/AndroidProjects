package e.welcome.sql_crudfunctionsapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText editsearch,editname,editemail,editage;
    SQLiteDatabase sqlitedb;
    Button Add,Delete,Update,Searchall,Search;
    String name,mailid,age,search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sqlitedb=openOrCreateDatabase("EmployeeDB", Context.MODE_PRIVATE,null);

        sqlitedb.execSQL("CREATE TABLE IF NOT EXISTS EmpRegistration(EmpId INTEGER PRIMARY KEY AUTOINCREMENT," +
                "EmpName VARCHAR(255),EmpMail VARCHAR(255),EmpAge VARCHAR(255));");
        editsearch=findViewById(R.id.itemsearch);
        editname=findViewById(R.id.itemname);
        editage=findViewById(R.id.itemage);
        editemail=findViewById(R.id.itememail);
        Add=findViewById(R.id.save);
        Delete=findViewById(R.id.delete);
        Update=findViewById(R.id.update);
        Searchall=findViewById(R.id.selectAll);
        Search=findViewById(R.id.Select);
        Add.setOnClickListener(this);
        Update.setOnClickListener(this);
        Delete.setOnClickListener(this);
        Searchall.setOnClickListener(this);
        Search.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.save)
        {
            name=editname.getText().toString().trim();
            mailid=editemail.getText().toString();
            age=editage.getText().toString();
            if(name.equals("")||mailid.equals("")||age.equals(""))
            {
                Toast.makeText(this, "Fields can not be empty", Toast.LENGTH_SHORT).show();
            }
            else
            {
                sqlitedb.execSQL("INSERT INTO EmpRegistration(EmpName,EmpMail,EmpAge)VALUES('"+name+"','"+mailid+
                "','"+age+"');");
                Toast.makeText(this, "Record saved", Toast.LENGTH_SHORT).show();
            }
        }
        else if(v.getId()==R.id.selectAll)
        {
            Cursor c=sqlitedb.rawQuery("Select * From EmpRegistration",null);
            if(c.getCount()==0)
            {
                Toast.makeText(this, "Database is empty", Toast.LENGTH_SHORT).show();
            }
            StringBuffer buffer=new StringBuffer();
            while(c.moveToNext())
            {
                buffer.append("Employee Name: "+c.getString(1)+"\n");
                buffer.append("Employee Mail: "+c.getString(2)+"\n");
                buffer.append("Employee Age: "+c.getString(3)+"\n");

            }
            Toast.makeText(this,buffer.toString(),Toast.LENGTH_SHORT).show();
        }
        else if(v.getId()==R.id.Select)
        {
            search=editsearch.getText().toString().trim();
           if(search.equals(""))
           {
               Toast.makeText(this, "Enter employee name", Toast.LENGTH_SHORT).show();
           }
           Cursor c=sqlitedb.rawQuery("Select * From EmpRegistration Where EmpName='"+search+"'",null);
           if(c.moveToFirst())
           {
               editname.setText(c.getString(1));
               editemail.setText(c.getString(2));
               editage.setText(c.getString(3));
           }
           else
           {
               Toast.makeText(this, "Data Not Found", Toast.LENGTH_SHORT).show();
           }}
          else if (v.getId()==R.id.update)
        {  search=editsearch.getText().toString().trim();
            name=editname.getText().toString().trim();
            mailid=editemail.getText().toString();
            age=editage.getText().toString();
            if(search.equals(""))
            {
                Toast.makeText(this, "Enter employee name", Toast.LENGTH_SHORT).show();
            }
            Cursor cursorupdate=sqlitedb.rawQuery("Select * From EmpRegistration Where EmpName='"+search+"'",null);
            if(cursorupdate.moveToFirst())
            {
                if(name.equals("")||mailid.equals("")||age.equals(""))
                {
                    Toast.makeText(this, "Fields can not be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    sqlitedb.execSQL("Update EmpRegistration set EmpName ='"+name+"',EmpMail='"+mailid+"',EmpAge='"+age+"' Where EmpName ='"+search+"'");
                    Toast.makeText(this, " Employee Updated", Toast.LENGTH_SHORT).show();
                }

            }
            else
            {
                Toast.makeText(this, "Data Not Found", Toast.LENGTH_SHORT).show();
            }
        }
          else if(v.getId() ==R.id.delete)
        {
            search=editsearch.getText().toString().trim();
            if(search.equals(""))
            {
                Toast.makeText(this, "Enter Employee name to delete", Toast.LENGTH_SHORT).show();
              return;
            }
            Cursor cursordelete=sqlitedb.rawQuery("Select * From EmpRegistration Where EmpName ='"+search+
                    "'",null);
            if(cursordelete.moveToFirst())
            {
                sqlitedb.execSQL("Delete From EmpRegistration Where EmpName ='"+search+"'");
                Toast.makeText(this, "Record deleted", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(this, "Data Not Found", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
