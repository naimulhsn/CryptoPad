package com.skillhunternaim.anotherloser.cryptopad;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NewNote extends AppCompatActivity {
    TextInputEditText textInputEditTextTitle,textInputEditTextText,textInputEditTextPassword;
    TextInputLayout textInputEditTextPasswordLayout;
    Button save_btn;
    CheckBox checkBox;
    SQLiteDataBaseOpenHelper sqLiteDataBaseOpenHelper;

    String password="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);
        textInputEditTextTitle=findViewById(R.id.id_new_note_title);
        textInputEditTextText=findViewById(R.id.id_new_note_text);
        textInputEditTextPassword=findViewById(R.id.id_new_note_password);
        textInputEditTextPasswordLayout=findViewById(R.id.id_new_note_password_layout);
        save_btn=findViewById(R.id.id_new_note_save_btn);
        checkBox=findViewById(R.id.id_new_note_check);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox.isChecked()){
                    textInputEditTextPasswordLayout.setVisibility(View.VISIBLE);
                    textInputEditTextPassword.setText("");
                }else {
                    textInputEditTextPasswordLayout.setVisibility(View.GONE);
                    textInputEditTextPassword.setText("");
                }
            }
        });
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(checkBox.isChecked()){
                    textInputEditTextPassword.setVisibility(View.VISIBLE);
                    textInputEditTextPassword.setText("");
                }else {
                    textInputEditTextPassword.setVisibility(View.GONE);
                    textInputEditTextPassword.setText("");
                }
            }
        });

        sqLiteDataBaseOpenHelper= new SQLiteDataBaseOpenHelper(this);
        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title=textInputEditTextTitle.getText().toString().trim();
                String text=textInputEditTextText.getText().toString().trim();
                password=textInputEditTextPassword.getText().toString().trim();
                Date currentTime = Calendar.getInstance().getTime();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
                String date = df.format(currentTime).trim();
                Boolean res=sqLiteDataBaseOpenHelper.addNote(title,text,date,password);
                if(res==true){
                    Toast.makeText(getApplicationContext(),"Successfully added",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(),Notes.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(getApplicationContext(),"Some error Encountered!!!",Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}
