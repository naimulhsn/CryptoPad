package com.skillhunternaim.anotherloser.cryptopad;

import android.content.Intent;
import android.content.SharedPreferences;
import android.opengl.Visibility;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPref;
    CardView enterPass, setPass;
    EditText e_pass, s_pass, c_pass;
    Button e_pass_btn, s_pass_btn;
    TextView wrong_pass,dont_match;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        enterPass=findViewById(R.id.id_app_card_enter_password);
        setPass=findViewById(R.id.id_app_card_set_password);
        e_pass=findViewById(R.id.id_app_password);
        s_pass=findViewById(R.id.id_app_set_password);
        c_pass=findViewById(R.id.id_app_set_password_confirm);
        e_pass_btn=findViewById(R.id.id_app_password_enter_btn);
        s_pass_btn=findViewById(R.id.id_app_set_password_btn);
        wrong_pass=findViewById(R.id.id_app_password_wrongtext);
        dont_match=findViewById(R.id.id_app_set_password_dont_match);

        sharedPref=getPreferences(MODE_PRIVATE);
        if(sharedPref.contains("password")){
            enterPass.setVisibility(View.VISIBLE);
            setPass.setVisibility(View.GONE);
        }else {
            setPass.setVisibility(View.VISIBLE);
            enterPass.setVisibility(View.GONE);
        }
        e_pass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(wrong_pass.getVisibility()==View.VISIBLE){
                    wrong_pass.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        c_pass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(dont_match.getVisibility()==View.VISIBLE){
                    dont_match.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    public void enter(View view){
        wrong_pass.setVisibility(View.GONE);
        String i_pass=e_pass.getText().toString().trim();
        String pref_pass=sharedPref.getString("password",null);
        if(i_pass.equals(pref_pass)){
            Intent intent=new Intent(this,Notes.class);
            startActivity(intent);
            finish();
        }else {
            wrong_pass.setVisibility(View.VISIBLE);
        }
    }
    public void pass_set(View view){
        String pass1=s_pass.getText().toString().trim();
        String pass2=c_pass.getText().toString().trim();
        if(!pass1.equals(pass2)|| pass1.equals("")){
            dont_match.setVisibility(View.VISIBLE);
        }else {
            sharedPref=getPreferences(MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("password",pass1);
            editor.apply();
            Intent intent=new Intent(this,Notes.class);
            startActivity(intent);
            finish();
        }
    }
}
