package com.example.mad_final_paper_practise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.mad_final_paper_practise.Database.DBHelper;

import java.util.ArrayList;

public class Student extends AppCompatActivity {

    DBHelper mydb;
    TextView wel;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        mydb = new DBHelper(this);
        wel = findViewById(R.id.studentWelcomeusernametxt2);
        listView = findViewById(R.id.studnetListview);

        wel.setText("Welcome "+getIntent().getExtras().getString("username"));

        ArrayList<String> list = new ArrayList<>();

        Cursor data = mydb.getListMessage();

        if(data.getCount() == 0){
            Toast.makeText(getApplicationContext(),"NO data",Toast.LENGTH_LONG).show();
        }
        else{
            while (data.moveToNext()){
                list.add(data.getString(2));
            }

            ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,list);
            listView.setAdapter(listAdapter);
        }

//        listView.setOnClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                String text = listView.getItemAtPosition(i).toString();
//                Toast.makeText(getApplicationContext()," ",Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(getApplicationContext(),Message.class);
//                intent.putExtra("subject",text);
//                startActivity(intent);
//
//            }
//        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String text = listView.getItemAtPosition(i).toString();
                Toast.makeText(getApplicationContext()," ",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),Message.class);
                intent.putExtra("subject",text);
                startActivity(intent);
            }
        });


    }
}
