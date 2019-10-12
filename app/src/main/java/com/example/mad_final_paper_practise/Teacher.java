package com.example.mad_final_paper_practise;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.mad_final_paper_practise.Database.DBHelper;

public class Teacher extends AppCompatActivity {

    DBHelper mydb;
    Button add;
    EditText sub, mess;
    TextView welcome;
    String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        mydb = new DBHelper(this);

        welcome = findViewById(R.id.teacherWelcomeusernametxt);
        add = findViewById(R.id.sendbtn);
        sub = findViewById(R.id.subjecttxtmes);
        mess = findViewById(R.id.messagetxt);
        user = getIntent().getExtras().getString("username");
        welcome.setText("Welcome " +getIntent().getExtras().getString("username"));
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
                clear();
            }
        });


    }

    public void sendMessage(){

        mydb.addMessage(user,sub.getText().toString(), mess.getText().toString());
        Toast.makeText(getApplicationContext(),"Message submited!!!",Toast.LENGTH_LONG).show();

    }

    public void clear(){
        sub.setText("");
        mess.setText("");
    }

}
