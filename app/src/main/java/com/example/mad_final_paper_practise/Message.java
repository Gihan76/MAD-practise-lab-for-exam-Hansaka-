package com.example.mad_final_paper_practise;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import com.example.mad_final_paper_practise.Database.DBHelper;
import com.example.mad_final_paper_practise.Database.Messagec;

public class Message extends AppCompatActivity {

    DBHelper mydb;
    TextView sub, mes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        mydb = new DBHelper(this);
        sub = findViewById(R.id.subjecttxtmes);
        mes = findViewById(R.id.messagetxtmes);

        String subject = getIntent().getExtras().getString("subject");

        Cursor data = mydb.getMessage(subject);

        Messagec m1 = new Messagec();

        if(data.getCount() == 0){
            Toast.makeText(getApplicationContext(),"Database is Empty",Toast.LENGTH_LONG).show();
        }
        else{

            while (data.moveToNext()){

                m1.setSubject(data.getString(2));
                m1.setMessage(data.getString(3));
            }
        }

        sub.setText(m1.getSubject());
        mes.setText(m1.getMessage());
    }
}
