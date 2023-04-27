package com.app.tour_ally;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyPlannerActivity extends AppCompatActivity {
    FloatingActionButton mFloatingActionBtn;
    EditText mEditTitle, mEditContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_notes_layout);

        showNotes();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference notesRef = db.collection("notes");

        mFloatingActionBtn = findViewById(R.id.foa_btn);

        mFloatingActionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Pop up dialog to enter title and content
                Dialog dialog = new Dialog(MyPlannerActivity.this);
                dialog.setContentView(R.layout.notes_popup_layout);

                mEditTitle = dialog.findViewById(R.id.note_title);
                mEditContent = dialog.findViewById(R.id.note_content);
                Button addNotesBtn = dialog.findViewById(R.id.notes_add_btn);

                addNotesBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String title = mEditTitle.getText().toString();
                        String content = mEditContent.getText().toString();

                        // Create a new note document with the title and content
                        Map<String, Object> note = new HashMap<>();
                        if (!title.isEmpty()) {
                            note.put("title", title);
                        }
                        if (!content.isEmpty()) {
                            note.put("content", content);
                        }

                        // Add the note to the "notes" collection in Firestore
                        notesRef.add(note)
                                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                    @Override
                                    public void onSuccess(DocumentReference documentReference) {
                                        // Retrieve the auto-generated ID of the new note
                                        String noteId = documentReference.getId();
                                        // Use the ID to construct the document reference
                                        DocumentReference noteRef = notesRef.document(noteId);
                                        // Do something with the new note document reference
                                        Toast.makeText(MyPlannerActivity.this, "Saved Successfully", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(MyPlannerActivity.this, "Not Saved", Toast.LENGTH_SHORT).show();
                                        Log.e("prem", "Error saving note: " + e.getMessage());
                                    }
                                });

                        showNotes();
                        dialog.dismiss();
                    }
                });

                //Optimized size for dialog
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                dialog.show();
                dialog.getWindow().setAttributes(lp);

            }
        });
    }


    public void showNotes() {

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Query the "notes" collection in Firestore
        db.collection("notes")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<Notes> notes = new ArrayList<>();
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            // Convert each document to a Note object
                            Notes note = documentSnapshot.toObject(Notes.class);
                            notes.add(note);
                        }

                        RecyclerView recyclerView = findViewById(R.id.notes_recycler);
                        GridLayoutManager layoutManager = new GridLayoutManager(MyPlannerActivity.this, 2); // two columns
                        recyclerView.setLayoutManager(layoutManager);
                        NotesAdapter adapter = new NotesAdapter(notes);
                        recyclerView.setAdapter(adapter);

                        ImageView no_notesImg = findViewById(R.id.no_content_img);
                        TextView no_contentText = findViewById(R.id.no_content_text);

                        if (notes.isEmpty()) {
                            no_notesImg.setVisibility(View.VISIBLE);
                            no_contentText.setVisibility(View.VISIBLE);
                        }

                        else {
                            no_notesImg.setVisibility(View.GONE);
                            no_contentText.setVisibility(View.GONE);
                        }

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Error retrieving notes
                    }
                });


    }

}
