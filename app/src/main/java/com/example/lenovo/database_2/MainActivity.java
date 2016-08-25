package com.example.lenovo.database_2;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    sqlite2 mydb2;
EditText e1,e2,e3;
    Button b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mydb2=new sqlite2(this);
        e1=(EditText)findViewById(R.id.id);
        e2=(EditText)findViewById(R.id.name);
        e3=(EditText)findViewById(R.id.surname);
        b1=(Button)findViewById(R.id.save);
        b2=(Button)findViewById(R.id.display);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isInserted=mydb2.insertData(e2.getText().toString(),e3.getText().toString());
                if(isInserted=true)
                    Toast.makeText(MainActivity.this,"data inserted",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,"data not inserted",Toast.LENGTH_LONG).show();

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                Cursor res=mydb2.getAlldata();
                if(res.getCount()==0)
                {
                    showMessage("Error","Nothing found");
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while(res.moveToNext())
                {
                    buffer.append("ID: "+ res.getString(0)+"\n");
                    buffer.append("Name: "+ res.getString(1)+"\n");
                    buffer.append("Surname: "+ res.getString(2)+"\n");

                }
                showMessage("Data ",buffer.toString());
            }
        });



    }

    public void showMessage(String Title,String Message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(Title);
        builder.setMessage(Message);
        builder.show();


    }
}
