package com.app.tour_ally;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

/*
Code for 'My Notes' section in drawer menu
*/
public class NotesFragment extends Fragment {

    RecyclerView mRecyclerView;
    ImageView mNoContentImg;
    FloatingActionButton mFloatingActionBtn;
    TextView mNoContentText;
    View mView;
    EditText mEditTitle, mEditContent;
    DatabaseHelper mDatabaseHelper;

    public NotesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_notes_layout, container, false);

        //Method calls
        showNotes();

        mFloatingActionBtn = mView.findViewById(R.id.foa_btn);
        mFloatingActionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Pop up dialog to enter title and content
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.notes_popup_layout);


                mEditTitle = dialog.findViewById(R.id.note_title);
                mEditContent = dialog.findViewById(R.id.note_content);
                Button addNotesBtn = dialog.findViewById(R.id.notes_add_btn);

                addNotesBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String title = mEditTitle.getText().toString();
                        String content = mEditContent.getText().toString();

                        if (!content.equals("")) {

                            mDatabaseHelper.notesDao().addNote(new Notes(title, content));
                            CollectionReference notesCollection = FirebaseFirestore.getInstance().collection("notes");
                            notesCollection.add(new Notes(title, content));
                            showNotes();
                            dialog.dismiss();

                        } else {
                            Toast.makeText(getContext(), "There's nothing to add", Toast.LENGTH_SHORT).show();
                        }
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

        return mView;
    }


    public void showNotes() {

        mRecyclerView = mView.findViewById(R.id.notes_recycler);
        mNoContentImg = mView.findViewById(R.id.no_content_img);
        mNoContentText = mView.findViewById(R.id.no_content_text);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mDatabaseHelper = DatabaseHelper.getInstance(getContext());

        ArrayList<Notes> arrNotes = (ArrayList<Notes>) mDatabaseHelper.notesDao().getNotes();
        if (arrNotes.size() > 0) {
            mRecyclerView.setVisibility(View.VISIBLE);
            mNoContentImg.setVisibility(View.GONE);
            mNoContentText.setVisibility(View.GONE);
            mRecyclerView.setAdapter(new NotesRecyclerAdapter(getContext(), arrNotes, mDatabaseHelper));

        } else {
            mRecyclerView.setVisibility(View.GONE);
            mNoContentImg.setVisibility(View.VISIBLE);
            mNoContentText.setVisibility(View.VISIBLE);
        }
    }


}