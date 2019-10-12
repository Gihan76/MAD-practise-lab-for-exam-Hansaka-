package com.example.mad_final_paper_practise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.mad_final_paper_practise.Database.DBHelper;
import com.example.mad_final_paper_practise.Database.User;

public class MainActivity extends AppCompatActivity {

    DBHelper mydb;
    Button Regis, log;
    EditText usname, pasw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mydb = new DBHelper(this);
        log = findViewById(R.id.loginBtn);
        usname = findViewById(R.id.usernametxt);
        pasw = findViewById(R.id.passwordtxt);
        Regis = findViewById(R.id.registerbtn);

        Regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Register.class);
                startActivity(intent);

            }
        });

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Cursor data = mydb.getUserList(usname.getText().toString());
                User u = new User();
                if(data.getCount() == 0){
                    Toast.makeText(getApplicationContext(),"Database Empty",Toast.LENGTH_LONG).show();
                }
                else{
                    while (data.moveToNext()){
                        u.setUsername(data.getString(1));
                        u.setPassword(data.getString(2));
                        u.setType(data.getString(3));
                    }

                    if(u.getPassword().equals(pasw.getText().toString()) && u.getType().equals("Teacher")){
                        Intent intent = new Intent(getApplicationContext(),Teacher.class);
                        intent.putExtra("username",usname.getText().toString());
                        startActivity(intent);
                    }
                    else if(u.getPassword().equals(pasw.getText().toString()) && u.getType().equals("Student")){
                        Intent intent = new Intent(getApplicationContext(),Student.class);
                        intent.putExtra("username",usname.getText().toString());
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Invalid Username or Password",Toast.LENGTH_LONG).show();
                    }
                }
            }

        });
    }
}
