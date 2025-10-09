package com.example.notatki;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<String> notes = new ArrayList<>();
    ArrayAdapter<String> arr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        notes.add("Zakupy: chleb, masło, ser");
        notes.add("Do zrobienia: obiad, umyć podłogi");
        notes.add("weekend: kino, spacer z psem");

        ListView list = findViewById(R.id.notes_list);

        arr = new ArrayAdapter<String>(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, notes);
        list.setAdapter(arr);
    }
    public void AddNote(View view) {
        EditText note_input = findViewById(R.id.note_input);
        String newNote = note_input.getText().toString().trim();
        ListView list = findViewById(R.id.notes_list);

        notes.add(newNote);
        arr.notifyDataSetChanged();
        note_input.setText("");
    }
}