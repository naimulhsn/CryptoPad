package com.skillhunternaim.anotherloser.cryptopad;

import android.content.DialogInterface;
import android.content.Intent;
import android.opengl.Visibility;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class Notes extends AppCompatActivity {
    SQLiteDataBaseOpenHelper sqLiteDataBaseOpenHelper;
    ListView listView;
    String date_textt;
    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        listView=findViewById(R.id.id_lists);
        sqLiteDataBaseOpenHelper=new SQLiteDataBaseOpenHelper(this);
        ArrayList<HashMap<String, String>> notesList= sqLiteDataBaseOpenHelper.getAllNoteList();
        NotesListAdapter adapter=new NotesListAdapter(this,notesList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView title=view.findViewById(R.id.id_custom_list_title);
                TextView date=view.findViewById(R.id.id_custom_list_creation_date);
                ImageView imageView=view.findViewById(R.id.id_custom_list_image);

                String title_text=title.getText().toString();
                String date_text=date.getText().toString();
                boolean pass=false;
                if(imageView.getVisibility()== View.VISIBLE){
                    pass=true;
                }else {
                    pass=false;
                }

                Intent intent=new Intent(getApplicationContext(),ShowNote.class);
                intent.putExtra("title",title_text);
                intent.putExtra("date",date_text);
                intent.putExtra("pass",pass);
                startActivity(intent);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                TextView date=view.findViewById(R.id.id_custom_list_creation_date);
                date_textt=date.getText().toString().trim();
                AlertDialog.Builder builder=new AlertDialog.Builder(Notes.this);
                builder.setMessage("Are you sure you want to delete this Note")
                        .setCancelable(true)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int res=sqLiteDataBaseOpenHelper.deleteNote(date_textt);
                                if(res>0) Toast.makeText(getApplicationContext(),"Deleted",Toast.LENGTH_SHORT).show();
                                else Toast.makeText(getApplicationContext(),"Error!!!",Toast.LENGTH_SHORT).show();
                                dialog.cancel();
                                Intent intent=new Intent(Notes.this, Notes.class);
                                startActivity(intent);
                                finish();

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alertDialog= builder.create();
                alertDialog.setTitle("Wait!!!");
                alertDialog.show();
                return true;
            }
        });

        fab=findViewById(R.id.id_add_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Add a new Note",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(getApplicationContext(),NewNote.class);
                startActivity(intent);
            }
        });
    }
}
