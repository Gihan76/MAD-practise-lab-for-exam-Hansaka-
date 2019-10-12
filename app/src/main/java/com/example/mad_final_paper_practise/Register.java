package com.example.mad_final_paper_practise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import com.example.mad_final_paper_practise.Database.DBHelper;

public class Register extends AppCompatActivity {

    Button reg;
    EditText uname, psw;
    CheckBox student, teacher;
    DBHelper dbHelper;
    String type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        dbHelper = new DBHelper(this);
        uname = findViewById(R.id.regusernametxt2);
        psw = findViewById(R.id.registerpasswordtxt);

        student = findViewById(R.id.studentchk);
        teacher = findViewById(R.id.teacherchk);

        reg = findViewById(R.id.registerbtn2);

        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(student.isChecked()){
                    type = "Student";
                }
            }
        });

        teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(teacher.isChecked()){
                    type = "Teacher";
                }
            }
        });
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Adduser();
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);

            }
        });


    }

    public void Adduser(){
        dbHelper.adinfo(uname.getText().toString(),psw.getText().toString(),type);
        Toast.makeText(getApplicationContext(),"Register Successfully!!!",Toast.LENGTH_LONG).show();
    }
}
