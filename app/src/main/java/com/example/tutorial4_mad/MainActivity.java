 package com.example.tutorial4_mad;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tutorial4_mad.database.DBHandler;

import java.util.List;

 public class MainActivity extends AppCompatActivity {

    Button add;
    EditText name;
    EditText pwd;

    Button update;
    Button delete;
    Button all;
    Button sign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.editTextUsername);
        pwd = findViewById(R.id.editTextPassword);

        sign = findViewById(R.id.buttonSign);

        all = findViewById(R.id.buttonSelectall);

        update = findViewById(R.id.buttonUpdate);
        delete = findViewById(R.id.buttonDelete);

        add = findViewById(R.id.buttonAdd);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBHandler dbHandler = new DBHandler(getApplicationContext());

                String usr = name.getText().toString();
                String pass = pwd.getText().toString();

                dbHandler.addInfo(usr, pass);
                Toast.makeText(MainActivity.this, "Successfull added. ", Toast.LENGTH_SHORT).show();
                name.setText("");
                pwd.setText("");
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHandler dbHandler = new DBHandler(getApplicationContext());

                String usr = name.getText().toString();
                String pass = pwd.getText().toString();

                dbHandler.updateInfo(usr, pass);
                Toast.makeText(MainActivity.this, "Successfull Updated. ", Toast.LENGTH_SHORT).show();
                name.setText("");
                pwd.setText("");

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHandler dbHandler = new DBHandler(getApplicationContext());

                String usr = name.getText().toString();


                dbHandler.deleteInfo(usr);
                Toast.makeText(MainActivity.this, "Successfull deleted. ", Toast.LENGTH_SHORT).show();
                name.setText("");
                pwd.setText("");
            }
        });


        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHandler dbHandler = new DBHandler(getApplicationContext());

                Cursor res = dbHandler.read();
                if (res.getCount() == 0){
                    show("Error", "nothing found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("ID : "+res.getString(0)+"\n");
                    buffer.append("Name : "+res.getString(1)+"\n");
                    buffer.append("Password : "+res.getString(2)+"\n\n");


                }
                show("Data",buffer.toString());

            }
        });

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHandler dbHandler = new DBHandler(getApplicationContext());

                String usr = name.getText().toString();
                String pass = pwd.getText().toString();

                Boolean result = dbHandler.check(usr, pass);

               if (result == true){
                    Toast.makeText(MainActivity.this,"You are Logged In Successfully",Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(MainActivity.this,"User is not Existing ",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    public  void show(String title, String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.show();
    }
}
