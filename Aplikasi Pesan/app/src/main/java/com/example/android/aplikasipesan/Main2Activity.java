package com.example.android.aplikasipesan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    Database DatabaseKu;
    private EditText edt1, edt2;
    Button nextAct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        edt1 = (EditText) findViewById(R.id.user);
        edt2 = (EditText) findViewById(R.id.pesan);
        nextAct = (Button) findViewById(R.id.kirim);
        DatabaseKu = new Database(this);

        nextAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = edt1.getText().toString();
                String message = edt2.getText().toString();
                if (edt1.length() != 0) {
                    AddData(user, message);
                    edt1.setText("");
                    edt2.setText("");
                    Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(Main2Activity.this, "Username can't be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void AddData(String user, String message){
        boolean insertData = DatabaseKu.addData(user, message);
        if(insertData){
            Toast.makeText(Main2Activity.this, "Data Sucessfully Inserted!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(Main2Activity.this, "Data Already Inserted!", Toast.LENGTH_SHORT).show();
        }
    }


}
