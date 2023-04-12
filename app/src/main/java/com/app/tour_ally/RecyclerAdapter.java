package com.app.tour_ally;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    Context context;
    ArrayList<Notes> arrayNotes;

    DatabaseHelper databaseHelper;

    public RecyclerAdapter(Context context, ArrayList<Notes> arrayNotes, DatabaseHelper databaseHelper) {
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

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtTitle, textContent;
        ImageView delete_icon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.text_title);
            textContent = itemView.findViewById(R.id.text_content);
            delete_icon = itemView.findViewById(R.id.delete_icon);

        }
    }


}
