package com.skillhunternaim.anotherloser.cryptopad;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ShowNote extends AppCompatActivity {
    CardView passCard,noteCard;
    SQLiteDataBaseOpenHelper sqLiteDataBaseOpenHelper;
    TextInputEditText password;
    Button btn;
    TextView title, text,date;
    String title_text,note_text,date_text;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_note);
        title=findViewById(R.id.id_show_note_title);
        text=findViewById(R.id.id_show_note_text);
        date=findViewById(R.id.id_show_note_date);

        passCard=findViewById(R.id.id_show_note_card_pass);
        noteCard=findViewById(R.id.id_show_note_card_note);
        password=findViewById(R.id.id_show_note_password);
        btn=findViewById(R.id.id_show_note_open_btn);


        sqLiteDataBaseOpenHelper=new SQLiteDataBaseOpenHelper(this);

        title_text=getIntent().getExtras().getString("title").trim();
        date_text=getIntent().getExtras().getString("date").trim();
        boolean pass=getIntent().getExtras().getBoolean("pass");

        if(pass){
            passCard.setVisibility(View.VISIBLE);
            noteCard.setVisibility(View.GONE);
        }else {
            passCard.setVisibility(View.GONE);
            noteCard.setVisibility(View.VISIBLE);
            note_text=sqLiteDataBaseOpenHelper.getNoteText(date_text);
            title.setText(title_text);
            text.setText(note_text);
            date.setText(date_text);
        }
    }
    public void matchPass(View view){
        String pass_text=sqLiteDataBaseOpenHelper.getPassText(date_text);
        String inp_pass=password.getText().toString().trim();
        if(pass_text.equals(inp_pass)){
            passCard.setVisibility(View.GONE);
            noteCard.setVisibility(View.VISIBLE);
            note_text=sqLiteDataBaseOpenHelper.getNoteText(date_text);
            title.setText(title_text);
            text.setText(note_text);
            date.setText(date_text);
        }else {
            password.setError("Wrong Password!!!");
        }
    }
}
