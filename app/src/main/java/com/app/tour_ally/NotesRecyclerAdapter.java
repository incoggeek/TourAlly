package com.app.tour_ally;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class NotesRecyclerAdapter extends RecyclerView.Adapter<NotesRecyclerAdapter.ViewHolder> {

    Context context;
    ArrayList<Notes> arrayNotes;

    DatabaseHelper databaseHelper;

    public NotesRecyclerAdapter(Context context, ArrayList<Notes> arrayNotes, DatabaseHelper databaseHelper) {
        this.context = context;
        this.arrayNotes = arrayNotes;
        this.databaseHelper = databaseHelper;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.notes_cardview_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtTitle.setText(arrayNotes.get(position).getTitle());
        holder.textContent.setText(arrayNotes.get(position).getContent());
        holder.notesRecycler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(context, ExpandedNotesActivity.class);
//                intent.putExtra("query",param1);
//                intent.putExtra("near",param2);
//                startActivity(intent);
            }
        });
        holder.delete_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                deleteNotes(holder.getAdapterPosition());
            }
        });

    }

    private void deleteNotes(int position) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Delete");
        builder.setMessage("Are you sure?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                databaseHelper.notesDao().deleteNotes(new Notes(arrayNotes.get(position).getId(),
                        arrayNotes.get(position).getTitle(), arrayNotes.get(position).getContent()));

                CollectionReference notesCollection = FirebaseFirestore.getInstance().collection("notes");
                notesCollection.document(arrayNotes.get(position).getTitle()).delete();
                Toast.makeText(context, "Deleted, Pls reload", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
       builder.show();
    }



    @Override
    public int getItemCount() {

        return arrayNotes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtTitle, textContent;
        ImageView delete_icon;
        RecyclerView notesRecycler;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.text_title);
            textContent = itemView.findViewById(R.id.text_content);
            delete_icon = itemView.findViewById(R.id.delete_icon);
            notesRecycler = itemView.findViewById(R.id.notes_recycler);

        }
    }

}
