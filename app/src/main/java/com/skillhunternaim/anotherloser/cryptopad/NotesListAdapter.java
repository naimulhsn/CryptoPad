package com.skillhunternaim.anotherloser.cryptopad;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class NotesListAdapter extends ArrayAdapter {
    Context mContext;
    ArrayList<HashMap<String, String>> notesList;
    public NotesListAdapter(Context context, ArrayList<HashMap<String, String>> notes) {
        super(context,R.layout.custom_list_node,notes);
        this.mContext=context;
        notesList=notes;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater= LayoutInflater.from(mContext);
        View view=layoutInflater.inflate(R.layout.custom_list_node,parent,false);
        TextView title=view.findViewById(R.id.id_custom_list_title);
        TextView date=view.findViewById(R.id.id_custom_list_creation_date);
        ImageView imageView=view.findViewById(R.id.id_custom_list_image);
        title.setText(notesList.get(position).get("title").trim());
        date.setText(notesList.get(position).get("date").trim());
        String pass=notesList.get(position).get("password").trim();
        if(pass.isEmpty() || pass.equals("")){
            imageView.setVisibility(View.GONE);
        }else {
            imageView.setVisibility(View.VISIBLE);
            imageView.setImageResource(R.drawable.ic_password);
        }
        return view;
    }
}
