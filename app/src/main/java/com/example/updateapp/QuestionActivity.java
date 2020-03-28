package com.example.updateapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;

public class QuestionActivity extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
//
        final String id = getIntent().getExtras().getString("id");
        final EditText Question = findViewById(R.id.Question);
        final EditText Option_1 = findViewById(R.id.option1);
        final EditText Option_2 = findViewById(R.id.option2);
        final EditText Option_3 = findViewById(R.id.option3);
        final EditText Option_4 = findViewById(R.id.option4);
        final EditText Correct_option = findViewById(R.id.correct_option);
        final EditText Explanation = findViewById(R.id.Explanation);

//        final String subj=sub.getText().toString();

        Button addq = findViewById(R.id.next_question);
        Button submit = findViewById(R.id.submit);
        addq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String question = Question.getText().toString().trim();
                final String opt1 = Option_1.getText().toString().trim();
                final String opt2 = Option_2.getText().toString().trim();
                final String opt3 = Option_3.getText().toString().trim();
                final String opt4 = Option_4.getText().toString().trim();
                final String coropt = Correct_option.getText().toString().trim();
                final String expl = Explanation.getText().toString().trim();

                QuestionModel model = new QuestionModel();

                model.setQuestion(question);
                model.setOption1(opt1);
                model.setOption2(opt2);
                model.setOption3(opt3);
                model.setOption4(opt4);
                model.setCorrectoption(coropt);
                model.setExplanation(expl);
//
//
                DocumentReference abc = db.collection("Passage").document(id).collection("Questions").document();

                HashMap<String, Object> newmap = new HashMap<>();
                newmap.put("id", abc.getId());
                newmap.put("question", question);
                newmap.put("reason", expl);

                ArrayList<String> options = new ArrayList<>();
                options.add(opt1);
                options.add(opt2);
                options.add(opt3);
                options.add(opt4);
                newmap.put("options", options);
                newmap.put("correctOption", coropt);
                abc.set(newmap);
                Question.setText("");
                Option_1.setText("");
                Option_2.setText("");
                Option_3.setText("");
                Option_4.setText("");
                Correct_option.setText("");
                Explanation.setText("");


            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newpassage();
            }
        });


    }

//    private void repeatact() {
//        Intent intent = new Intent(this, QuestionActivity.class);
//        startActivity(intent);
//        finish();
//    }

    public void newpassage() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }


}
