package com.example.updateapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.HashMap;

import javax.security.auth.Subject;

public class AddPassage extends AppCompatActivity {
    Button AddButton;

    EditText PassageEditText, SubjectEditText;
    String PassageHolder, SubjectHolder;


//    DatabaseReference databaseReference;

    //    public static final String Database_Path = "Passage";
     FirebaseFirestore db = FirebaseFirestore.getInstance();

//    public String id = ref.getId();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
//        databaseReference = FirebaseDatabase.getInstance().getReference(Database_Path);

//        UpdateButton = (Button) findViewById(R.id.update);
        AddButton = (Button) findViewById(R.id.addquestion);
        PassageEditText = (EditText) findViewById(R.id.passage);

        SubjectEditText = (EditText) findViewById(R.id.subject);

        AddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Passage passage = new Passage();

                GetDataFromEditText();

                passage.setPassage(PassageHolder);


                passage.setSubject(SubjectHolder);
                DocumentReference ref = db.collection("Passage").document();
//                String IDFromServer = databaseReference.push().getKey();
                HashMap<String, Object> map = new HashMap<>();
                map.put("subject", SubjectHolder);
                map.put("passage", PassageHolder);
                map.put("passage id", ref.getId());

                ref.set(map);
                Snackbar.make(view, "Added the passage", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                nextact(view);

            }
        });


    }

//
    public void nextact(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void GetDataFromEditText() {
        PassageHolder = PassageEditText.getText().toString().trim();

        SubjectHolder = SubjectEditText.getText().toString().trim();
    }


}












